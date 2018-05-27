package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.Booking;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import es.ulpgc.eite.clean.mvp.sample.app.Timetable;
import es.ulpgc.eite.clean.mvp.sample.data.DatabaseFacade;


public class CalendarModel
    extends GenericModel<Calendar.ModelToPresenter> implements Calendar.PresenterToModel {

  private DatabaseReference connection;
  private FirebaseDatabase database;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Calendar.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");
    //db = DatabaseFacade.getInstance();

    // Conectar con la BBDD
    database = FirebaseDatabase.getInstance();
    // Generar una referencia con la que conectar.
    connection = database.getReference();
  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }


  @Override
  public void setBooking(final Booking booking, Shop shop) {
    getPresenter().setAppointment();
    connection.child("booking").push().setValue(booking);
    // TODO: 26/5/18 Mandar mail
  }

  @Override
  public void setTimetableList(final String date, Shop shop) {
    connection.child("timetable").child(Integer.toString(shop.getTimetable())).child("timetable").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        ArrayList<Timetable> hours;
        GenericTypeIndicator<ArrayList<Timetable>> indicator = new GenericTypeIndicator<ArrayList<Timetable>>() {};
        hours = dataSnapshot.getValue(indicator);
        checkTimetableOnDate(date, hours);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        // TODO: 27/5/18 Poner que guarde tire de Realm
      }
    });
  }

  private void checkTimetableOnDate(final String date, final ArrayList<Timetable> hours) {
    connection.child("booking").addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {
        if (dataSnapshot.getValue() != null){
          GenericTypeIndicator<ArrayList<Booking>> indicator = new GenericTypeIndicator<ArrayList<Booking>>() {};
          ArrayList<Booking> bookings = dataSnapshot.getValue(indicator);
          for (Booking item: bookings){
            if (item.getDate() == date) {
              hours.remove(item.getHourId());
            }
          }
          getPresenter().setAvailableHours(hours);
        } else {
          getPresenter().setAvailableHours(hours);
        }
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        // TODO: 27/5/18 Poner que guarde tire de Realm
      }
    });
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////



}
