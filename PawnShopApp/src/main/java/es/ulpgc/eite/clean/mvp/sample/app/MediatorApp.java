package es.ulpgc.eite.clean.mvp.sample.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import es.ulpgc.eite.clean.mvp.sample.calendar.Calendar;
import es.ulpgc.eite.clean.mvp.sample.calendar.CalendarView;
import es.ulpgc.eite.clean.mvp.sample.chat.Chat;
import es.ulpgc.eite.clean.mvp.sample.chat.ChatView;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.maps.Maps;
import es.ulpgc.eite.clean.mvp.sample.maps.MapsView;
import es.ulpgc.eite.clean.mvp.sample.webshop.Webshop;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;


public class MediatorApp extends Application implements Mediator.Lifecycle, Mediator.Navigation {

  protected final String TAG = this.getClass().getSimpleName();

  private CalendarState toCalendarState;
  private MapState toMapsState;
  private ChatState toChatState;
  private ShopState toShopState;

  @Override
  public void onCreate() {
    super.onCreate();
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "calling creatingInitialState()");
    }

  ///////////////////////////////////////////////////////////////////////////////////
  // Lifecycle /////////////////////////////////////////////////////////////////////


  @Override
  public void startingScreen(Maps.ToMaps presenter) {
    if (toMapsState != null) {
      Log.d(TAG, "calling settingMapsState()");
      presenter.setShop(toMapsState.shop);

      Log.d(TAG, "calling removingInitialMapsState()");
      toMapsState = null;
    }
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
    if (toCalendarState != null) {
      Log.d(TAG, "calling settingCalendarState()");
      presenter.setShop(toCalendarState.shop);
      presenter.setAppointment(toCalendarState.ifAppointment);
      presenter.setNameInputText(toCalendarState.name);
      presenter.setPhoneInputText(toCalendarState.phone);
      presenter.setMailInputText(toCalendarState.mail);
      presenter.setProductsInputText(toCalendarState.products);

      Log.d(TAG, "calling removingInitialCalendarState()");
      toCalendarState = null;
    }
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Calendar.CalendarTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Chat.ToChat presenter) {
    if (toChatState != null) {
      Log.d(TAG, "calling settingChatState()");
      presenter.setShop(toChatState.shop);

      Log.d(TAG, "calling removingInitialChatState()");
      toChatState = null;
    }
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Chat.ChatTo presenter) {
    presenter.onScreenResumed();
  }

  @Override
  public void startingScreen(Webshop.ToWebshop presenter) {
    if (toShopState != null) {
      Log.d(TAG, "calling settingChatState()");
      presenter.setShop(toShopState.shop);

      Log.d(TAG, "calling removingInitialChatState()");
      toShopState = null;
    }
    presenter.onScreenStarted();
  }

  @Override
  public void resumingScreen(Webshop.WebshopTo presenter) {
    presenter.onScreenResumed();
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Navigation ////////////////////////////////////////////////////////////////////


  @Override
  public void goToNextScreen(Maps.MapsTo presenter) {
    if (presenter.isChatClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      toChatState = new ChatState();
      toChatState.shop = presenter.getShop();

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingChatScreen()");
        view.startActivity(new Intent(view, ChatView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isCalendarClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO

      if (toCalendarState == null) {
        toCalendarState = new CalendarState();
        toCalendarState.shop = presenter.getShop();
      }

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingCalendarScreen()");
        view.startActivity(new Intent(view, CalendarView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isShopClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO

      toShopState = new ShopState();
      toShopState.shop = presenter.getShop();
      toShopState.url = "https://canarias.cashconverters.es";

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingWebshopScreen()");
        view.startActivity(new Intent(view, WebshopView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    }
  }


  @Override
  public void backToPreviousScreen(Maps.MapsTo presenter) {

  }

  @Override
  public void goToNextScreen(Home.HomeTo presenter) {
    // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
    toMapsState = new MapState();
    toMapsState.shop = presenter.getShop();

    Context view = presenter.getManagedContext();
    if (view != null) {
      Log.d(TAG, "calling startingMapsScreen()");
      view.startActivity(new Intent(view, MapsView.class));
      Log.d(TAG, "calling destroyView()");
      presenter.destroyView();
    }
  }

  @Override
  public void backToPreviousScreen(Home.HomeTo presenter) {

  }

  @Override
  public void goToNextScreen(Calendar.CalendarTo presenter) {
    Log.d(TAG, "saving CalendarState()");

    toCalendarState = new CalendarState();
    toCalendarState.shop = presenter.getShop();
    toCalendarState.name = presenter.getNameInputText();
    toCalendarState.mail = presenter.getMailInputText();
    toCalendarState.phone = presenter.getPhoneInputText();
    toCalendarState.products = presenter.getProductsInputText();

    if (presenter.isChatClicked()){
      toChatState = new ChatState();
      toChatState.shop = presenter.getShop();

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingChatScreen()");
        view.startActivity(new Intent(view, ChatView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isShopClicked()){
      toShopState = new ShopState();
      toShopState.shop = presenter.getShop();
      toShopState.url = "https://canarias.cashconverters.es";

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingWebshopScreen()");
        view.startActivity(new Intent(view, WebshopView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isMapsClicked()){
      toMapsState = new MapState();
      toMapsState.shop = presenter.getShop();

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingMapsScreen()");
        view.startActivity(new Intent(view, MapsView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    }
  }

  @Override
  public void backToPreviousScreen(Calendar.CalendarTo presenter) {

  }

  @Override
  public void goToNextScreen(Chat.ChatTo presenter) {
    if (presenter.isCalendarClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      if (toCalendarState == null) {
        toCalendarState = new CalendarState();
        toCalendarState.shop = presenter.getShop();
      }

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingCalendarScreen()");
        view.startActivity(new Intent(view, CalendarView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isShopClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      toShopState = new ShopState();
      toShopState.shop = presenter.getShop();
      toShopState.url = "https://canarias.cashconverters.es";

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingWebshopScreen()");
        view.startActivity(new Intent(view, WebshopView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isMapsClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      toMapsState = new MapState();
      toMapsState.shop = presenter.getShop();


      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingMapsScreen()");
        view.startActivity(new Intent(view, MapsView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    }
  }

  @Override
  public void backToPreviousScreen(Chat.ChatTo presenter) {

  }

  @Override
  public void goToNextScreen(Webshop.WebshopTo presenter) {
    if (presenter.isChatClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      toChatState = new ChatState();
      toChatState.shop = presenter.getShop();
      toChatState.idChat = null;

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingChatScreen()");
        view.startActivity(new Intent(view, ChatView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isCalendarClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      if (toCalendarState == null) {
        toCalendarState = new CalendarState();
        toCalendarState.shop = presenter.getShop();
      }

      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingCalendarScreen()");
        view.startActivity(new Intent(view, CalendarView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    } else if (presenter.isMapsClicked()){
      // TODO: Prueba de estado, CAMBIAR CUÁNDO HAYA ESTADO
      toMapsState = new MapState();
      toMapsState.shop = presenter.getShop();


      Context view = presenter.getManagedContext();
      if (view != null) {
        Log.d(TAG, "calling startingMapsScreen()");
        view.startActivity(new Intent(view, MapsView.class));
        Log.d(TAG, "calling destroyView()");
        presenter.destroyView();
      }
    }
  }

  @Override
  public void backToPreviousScreen(Webshop.WebshopTo presenter) {
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

  private class ShopState {
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
