package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
  public ArrayList<String> getTimetable(Shop shop) {
    ArrayList<String> hours = new ArrayList<>();
    ArrayList<Timetable> calendar;
    calendar = shop.getTimetable();
    if (calendar.size() > 0) {
      for (int i = 0; i < calendar.size(); i++) {
        if (!calendar.get(i).isBusy()){
          hours.add(calendar.get(i).getHour());
        }
      }
    } else {
      hours.add("No hay horas disponibles.");
    }
    return hours;
  }

  @Override
  public void setBooking(Booking booking, Shop shop) {
    shop.getTimetable().get(booking.getShopId()).setBusy(true);
    getPresenter().setAppointment();
    connection.child("booking").setValue(booking);
    // TODO: 26/5/18 Mandar mail
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////



}
