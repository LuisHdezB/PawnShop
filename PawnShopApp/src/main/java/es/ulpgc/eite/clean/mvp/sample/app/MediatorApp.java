package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.calendar.Calendar;
import es.ulpgc.eite.clean.mvp.sample.chat.Chat;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.maps.Maps;
import es.ulpgc.eite.clean.mvp.sample.webshop.Webshop;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  //private DummyState toDummyState;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling creatingInitialState()");
    /* Heradado de Dummy
    toDummyState = new DummyState();
    toDummyState.toolbarVisibility = false;
    toDummyState.textVisibility = false;
    */
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////


  @Override
  public void startingScreen(Maps.ToMaps presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Maps.MapsTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Home.ToHome presenter) {
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Home.HomeTo presenter) {
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
  public void goToNextScreen(Maps.MapsTo presenter) {
    if (presenter.isShopClicked()){
      // TODO: Guardar estado
      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingWebshopScreen()");
        view.startActivity(new Intent(view, WebshopView.class));
        presenter.destroyView();
      }
    }
  }

  @Override
  public void backToPreviousScreen(Maps.MapsTo presenter) {

  }

  @Override
  public void goToNextScreen(Home.HomeTo presenter) {

  }

  @Override
  public void backToPreviousScreen(Home.HomeTo presenter) {

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

  private class MapState {
    Shop shop;
  }

  private class ChatState {
    Shop shop;
    String idChat;
  }

  private class WebState {
    Shop shop;
    String url;
  }

  private class CalendarState {
    Shop shop;
    String date;
    String hour;
    boolean ifAppointment;
    String name;
    String mail;
    int phone;
    String products;
  }
}
