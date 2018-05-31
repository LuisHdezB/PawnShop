package es.ulpgc.eite.clean.mvp.sample.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.GenericPresenter;
import es.ulpgc.eite.clean.mvp.sample.app.Mediator;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;

public class HomePresenter
    extends GenericPresenter
        <Home.PresenterToView, Home.PresenterToModel, Home.ModelToPresenter, HomeModel>
    implements Home.ViewToPresenter, Home.ModelToPresenter, Home.HomeTo, Home.ToHome {

  private static final String KEY_SHOP = "shopId";
  private static final String SHOP_PREFERENCES = "SHOP";
  private Shop shop;

  /**
   * Operation called during VIEW creation in {@link GenericActivity#onResume(Class, Object)}
   * Responsible to initialize MODEL.
   * Always call {@link GenericPresenter#onCreate(Class, Object)} to initialize the object
   * Always call  {@link GenericPresenter#setView(ContextView)} to save a PresenterToView reference
   *
   * @param view The current VIEW instance
   */
  @Override
  public void onCreate(Home.PresenterToView view) {
    super.onCreate(HomeModel.class, this);
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
  public void onResume(Home.PresenterToView view) {
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
  public void onButtonClicked(int position) {
    Log.d(TAG, "calling onButtonClicked()");
    getModel().getShopAsync(position);



  }

  @Override
  public void startLoadShopList() {
    Log.d(TAG, "calling startLoadShopList()");
    getModel().loadShopList();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////



  ///////////////////////////////////////////////////////////////////////////////////
  // To Home ///////////////////////////////////////////////////////////////////////

  @Override
  public void onScreenStarted() {
    Log.d(TAG, "calling onScreenStarted()");
    setCurrentState();
  }

  @Override
  public void onScreenResumed() {
    Log.d(TAG, "calling onScreenResumed()");

    setCurrentState();
  }

  @Override
  public Shop getShop() {
    return shop;
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Home To ///////////////////////////////////////////////////////////////////////


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


  ///////////////////////////////////////////////////////////////////////////////////


  private void setCurrentState() {
    Log.d(TAG, "calling setCurrentState()");

  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Model To Presenter ////////////////////////////////////////////////////////////

  @Override
  public void setShopList(ArrayList<String> names) {
    if (isViewRunning()){
      getView().setShopNames(names);
    }
  }

  @Override
  public void setShopSelected(Shop shopSelected) {
    shop = shopSelected;

    SharedPreferences preferences = getManagedContext().getSharedPreferences(SHOP_PREFERENCES, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt(KEY_SHOP,shop.getId());
    editor.apply();

    Mediator.Navigation mediator = (Mediator.Navigation) getApplication();
    mediator.goToNextScreen(this);
  }
}
