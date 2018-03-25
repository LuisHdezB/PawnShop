package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.calendar.Calendar;
import es.ulpgc.eite.clean.mvp.sample.chat.Chat;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.maps.Maps;
import es.ulpgc.eite.clean.mvp.sample.webshop.Webshop;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private DummyState toDummyState;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling creatingInitialState()");
    toDummyState = new DummyState();
    toDummyState.toolbarVisibility = false;
    toDummyState.textVisibility = false;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////


  @Override
  public void startingScreen(Maps.ToDummy presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Maps.DummyTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Home.ToDummy presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Home.DummyTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Calendar.ToCalendar presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Calendar.CalendarTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Chat.ToDummy presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Chat.DummyTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Webshop.ToDummy presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Webshop.DummyTo presenter) {
    presenter.onScreenResumed();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigation ////////////////////////////////////////////////////////////////////


  @Override
  public void goToNextScreen(Maps.DummyTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Maps.DummyTo presenter) {

  }

  @Override
  public void goToNextScreen(Home.DummyTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Home.DummyTo presenter) {

  }

  @Override
  public void goToNextScreen(Calendar.CalendarTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Calendar.CalendarTo presenter) {

  }

  @Override
  public void goToNextScreen(Chat.DummyTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Chat.DummyTo presenter) {

  }

  @Override
  public void goToNextScreen(Webshop.DummyTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Webshop.DummyTo presenter) {

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class DummyState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }

}
