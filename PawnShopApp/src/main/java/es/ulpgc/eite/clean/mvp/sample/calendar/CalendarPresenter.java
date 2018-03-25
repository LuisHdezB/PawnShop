package es.ulpgc.eite.clean.mvp.sample.calendar;


import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;

public class CalendarPresenter
    extends GenericPresenter
        <Calendar.PresenterToView, Calendar.PresenterToModel, Calendar.ModelToPresenter, CalendarModel>
    implements Calendar.ViewToPresenter, Calendar.ModelToPresenter, Calendar.CalendarTo, Calendar.ToCalendar {

  private boolean toolbarVisible;
  private boolean buttonClicked;
  private boolean textVisible;

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
  public void onButtonClicked() {
    Log.d(TAG, "calling onButtonClicked()");
    /* Heredado de Dummy
    if(getModel().isNumOfTimesCompleted()){

      getModel().resetMsgByBtnClicked(); // reseteamos el estado al cumplirse la condición

      Log.d(TAG, "calling goToNextScreen()");
      Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
      mediator.goToNextScreen(this);
      return;
    }

    if(isViewRunning()) {
      getModel().changeMsgByBtnClicked();
      getView().setText(getModel().getText());
      textVisible = true;
      buttonClicked = true;
      checkTextVisibility();
    }
    */

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////


/* Heredado de Dummy
  @Override
  public void setToolbarVisibility(boolean visible) {
    toolbarVisible = visible;
  }

  @Override
  public void setTextVisibility(boolean visible) {
    textVisible = visible;
  }
  */


  ///////////////////////////////////////////////////////////////////////////////////
  // To Dummy //////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
    /* Heredado de Dummy
    if (buttonClicked) {
      getView().setText(getModel().getText());
    }
    */
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Dummy To //////////////////////////////////////////////////////////////////////


  @Override
  public Context getManagedContext(){
    return getActivityContext();
  }

  @Override
  public void destroyView(){
    if(isViewRunning()) {
      getView().finishScreen();
    }
  }

/* Heredado de Dummy
  @Override
  public boolean isToolbarVisible() {
    return toolbarVisible;
  }

  @Override
  public boolean isTextVisible() {
    return textVisible;
  }*/


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");
    /* Hererado de Dummy
    if(isViewRunning()) {

      getView().setLabel(getModel().getLabel());

    }
    checkToolbarVisibility();
    checkTextVisibility();
    */
  }

/* Heredado de Dummy
  private void checkToolbarVisibility(){
    if(isViewRunning()) {
      if (!toolbarVisible) {
        getView().hideToolbar();
      }
    }
  }

  private void checkTextVisibility(){
    if(isViewRunning()) {
      if(!textVisible) {
        getView().hideText();
      } else {
        getView().showText();
      }
    }
  }
  */

}
