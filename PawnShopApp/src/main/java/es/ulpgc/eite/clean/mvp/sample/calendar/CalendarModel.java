package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import es.ulpgc.eite.clean.mvp.sample.data.DatabaseFacade;


public class CalendarModel
    extends GenericModel<Calendar.ModelToPresenter> implements Calendar.PresenterToModel {

    DatabaseFacade db;

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
    db = DatabaseFacade.getInstance();
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
    ArrayList<Shop.Timetable> calendar;
    calendar = shop.getTimetable();
    if (calendar.size() > 0) {
      for (int i = 0; i < calendar.size(); i++) {
        hours.add(calendar.get(i).getHour());
      }
    } else {
      hours.add("No hay horas disponibles.");
    }
    return hours;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////



}
