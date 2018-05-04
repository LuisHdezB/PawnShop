package es.ulpgc.eite.clean.mvp.sample.chat;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;

/**
 * Created by Luis on 12/11/16.
 */

public interface Chat {


  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {
  }

  interface ToChat extends State {
    void onScreenStarted();
    void setShop(Shop shop);

  }

  interface ChatTo extends State{
    Context getManagedContext();
    void destroyView();
    boolean isShopClicked();
    boolean isCalendarClicked();
    boolean isMapsClicked();
    void onScreenResumed();

    Shop getShop();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onShopButtonClicked();
    void onMapsButtonClicked();
    void onCalendarButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
  }

  /**
   * Methods offered to MODEL to communicate with PRESENTER
   */
  interface PresenterToModel extends Model<ModelToPresenter> {

  }

  /**
   * Required PRESENTER methods available to MODEL
   */
  interface ModelToPresenter {

  }

}
