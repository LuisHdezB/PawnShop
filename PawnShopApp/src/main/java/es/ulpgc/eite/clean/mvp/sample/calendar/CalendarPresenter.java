package es.ulpgc.eite.clean.mvp.sample.calendar;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;

public class CalendarPresenter
    extends GenericPresenter
        <Calendar.PresenterToView, Calendar.PresenterToModel, Calendar.ModelToPresenter, CalendarModel>
    implements Calendar.ViewToPresenter, Calendar.ModelToPresenter, Calendar.CalendarTo, Calendar.ToCalendar {

  private boolean shopClicked;
  private boolean chatClicked;
  private boolean mapsClicked;
  //State
  private Shop shop;
  private String name, mail, date, hour, products;
  private boolean ifAppointment;
  private int phone;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Calendar.PresenterToView view) {
    super.onCreate(CalendarModel.class, this);
    setView(view);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling startingScreen()");
    Mediator.Lifecycle mediator = (Mediator.Lifecycle) getApplication();
    mediator.startingScreen(this);
  }

  /**
   * Operation called by VIEW after its reconstruction.
   * Always call {@link GenericPresenter#setView(ContextView)}
   * to save the new instance of PresenterToView
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onResume(Calendar.PresenterToView view) {
    setView(view);
    Log.d(TAG, "calling onResume()");

    Mediator.Lifecycle mediator = (Mediator.Lifecycle) getApplication();
    mediator.resumingScreen(this);
  }

  /**
   * Helper method to inform Presenter that a onBackPressed event occurred
   * Called by {@link GenericActivity}
   */
  @Override
  public void onBackPressed() {
    Log.d(TAG, "calling onBackPressed()");

    Log.d(TAG, "calling backToPreviousScreen()");
    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.backToPreviousScreen(this);
  }

  /**
   * Hook method called when the VIEW is being destroyed or
   * having its configuration changed.
   * Responsible to maintain MVP synchronized with Activity lifecycle.
   * Called by onDestroy methods of the VIEW layer, like: {@link GenericActivity#onDestroy()}
   *
   * @param isChangingConfiguration true: configuration changing & false: being destroyed
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {
    super.onDestroy(isChangingConfiguration);

    if(isChangingConfiguration) {
      Log.d(TAG, "calling onChangingConfiguration()");
    } else {
      Log.d(TAG, "calling onDestroy()");
    }
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // View To Presenter /////////////////////////////////////////////////////////////

  @Override
  public void onShopButtonClicked() {
    Log.d(TAG, "calling onShopButtonClicked()");
    //TODO: Poner estado
    shopClicked = true;

    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.goToNextScreen(this);
  }

  @Override
  public void onMapsButtonClicked() {
    Log.d(TAG, "calling onMapsButtonClicked()");
    //TODO: Poner estado
    mapsClicked = true;

    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.goToNextScreen(this);
  }

  @Override
  public void onChatButtonClicked() {
    Log.d(TAG, "calling onMapsButtonClicked()");
    //TODO: Poner estado
    chatClicked = true;

    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.goToNextScreen(this);
  }

  @Override
  public void onSendButtonClicked() {
    // TODO: 8/4/18 Crear método de envio de cita
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////



  ///////////////////////////////////////////////////////////////////////////////////
  // To Calendar //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  @Override
  public void setShop(Shop shop) {
    this.shop = shop;
  }

  @Override
  public void setAppointment(boolean ifAppointment) {
    this.ifAppointment = ifAppointment;
  }

  @Override
  public void setNameInputText(String name) {
    this.name = name;
  }

  @Override
  public void setPhoneInputText(int phone) {
    this.phone = phone;
  }

  @Override
  public void setMailInputText(String mail) {
    this.mail = mail;
  }

  @Override
  public void setDateInputText(String date) {
    this.date = date;
  }

  @Override
  public void setHourInputText(String hour) {
    this.hour = hour;
  }

  @Override
  public void setProductsInputText(String products) {
    this.products = products;
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
  }

  @Override
  public boolean isShopClicked() {
    return shopClicked;
  }

  @Override
  public boolean isChatClicked() {
    return chatClicked;
  }

  @Override
  public boolean isMapsClicked() {
    return mapsClicked;
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Calendar To //////////////////////////////////////////////////////////////////////

  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    Log.d(TAG, "destroyView: calling finishScreen");
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

    //Datos de prueba:
    date = "8/4/2018";
    hour = "12:00";
    name = "alex";
    phone = 123456789;
    mail = "alex@alex.com";
    ifAppointment = false;

    if (isViewRunning()) {
      getView().setDateView(date);
      getView().setHourBar(hour);
      getView().setHourText(hour);
      getView().setNameText(name);
      getView().setPhoneText(phone);
      getView().setMailText(mail);
    }
    checkButtonEnable();
  }

  private void checkButtonEnable() {
    if (isViewRunning()) {
      if (!ifAppointment) {
        getView().enableSendButton();
      } else {
        getView().disableSendButon();
      }
    }
  }

}
