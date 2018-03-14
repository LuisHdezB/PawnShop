package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;
import es.ulpgc.eite.clean.mvp.sample.dummy.DummyView;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private DummyState toDummyState, dummyToState;

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
  public void startingScreen(Dummy.ToDummy presenter){
    if(toDummyState != null) {
      Log.d(TAG, "calling settingInitialState()");
      presenter.setToolbarVisibility(toDummyState.toolbarVisibility);
      presenter.setTextVisibility(toDummyState.textVisibility);

      Log.d(TAG, "calling removingInitialState()");
      toDummyState = null;
    }

    if(dummyToState != null) {
      Log.d(TAG, "calling settingUpdatedState()");
      presenter.setToolbarVisibility(dummyToState.toolbarVisibility);
      presenter.setTextVisibility(dummyToState.textVisibility);

      Log.d(TAG, "calling removingUpdateState()");
      dummyToState = null;
    }

    presenter.onScreenStarted();
  }


  @Override
  public void resumingScreen(Dummy.DummyTo presenter){
    if(dummyToState != null) {
      Log.d(TAG, "calling resumingScreen()");
      Log.d(TAG, "calling restoringUpdatedState()");
      presenter.setToolbarVisibility(dummyToState.toolbarVisibility);
      presenter.setTextVisibility(dummyToState.textVisibility);

      Log.d(TAG, "calling removingUpdatedState()");
      dummyToState = null;
    }

    presenter.onScreenResumed();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigation ////////////////////////////////////////////////////////////////////


  @Override
  public void backToPreviousScreen(Dummy.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.textVisibility = true;
    dummyToState.toolbarVisibility = false;
  }

  @Override
  public void goToNextScreen(Dummy.DummyTo presenter) {
    Log.d(TAG, "calling savingUpdatedState()");
    dummyToState = new DummyState();
    dummyToState.toolbarVisibility = presenter.isToolbarVisible();
    //dummyToState.textVisibility = presenter.isTextVisible();
    dummyToState.textVisibility = false;

    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingNextScreen()");
      view.startActivity(new Intent(view, DummyView.class));
      //Log.d(TAG, "calling finishingCurrentScreen()");
      //presenter.destroyView();
    }

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  private class DummyState {
    boolean toolbarVisibility;
    boolean textVisibility;
  }

}
