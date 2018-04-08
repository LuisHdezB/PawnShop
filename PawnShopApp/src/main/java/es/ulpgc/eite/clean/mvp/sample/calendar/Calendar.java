package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.content.Context;

import es.ulpgc.eite.clean.mvp.ContextView;
import es.ulpgc.eite.clean.mvp.Model;
import es.ulpgc.eite.clean.mvp.Presenter;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;


public interface Calendar {

  ///////////////////////////////////////////////////////////////////////////////////
  // State /////////////////////////////////////////////////////////////////////////

  interface State {

  }

  interface ToCalendar extends State {
    void onScreenStarted();
    void setShop(Shop shop);
    void setAppointment(boolean ifAppointment);
    void setNameInputText(String name);
    void setPhoneInputText(int phone);
    void setMailInputText(String mail);
    void setDateInputText(String date);
    void setHourInputText(String hour);
    void setProductsInputText(String products);
  }

  interface CalendarTo extends State{
    Context getManagedContext();
    void destroyView();
    void onScreenResumed();
    boolean isShopClicked();
    boolean isChatClicked();
    boolean isMapsClicked();
  }

  ///////////////////////////////////////////////////////////////////////////////////
  // Screen ////////////////////////////////////////////////////////////////////////

  /**
   * Methods offered to VIEW to communicate with PRESENTER
   */
  interface ViewToPresenter extends Presenter<PresenterToView> {
    void onShopButtonClicked();
    void onMapsButtonClicked();
    void onChatButtonClicked();
    void onSendButtonClicked();
  }

  /**
   * Required VIEW methods available to PRESENTER
   */
  interface PresenterToView extends ContextView {
    void finishScreen();
    void setDateView(String date);
    void setHourBar(String hour);
    void setHourText(String hour);
    void setNameText(String name);
    void setPhoneText(int phone);
    void setMailText(String mail);
    void enableSendButton();
    void disableSendButon();
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
