package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.calendar.Calendar;
import es.ulpgc.eite.clean.mvp.sample.chat.Chat;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.maps.Maps;
import es.ulpgc.eite.clean.mvp.sample.webshop.Webshop;


public interface Mediator {

  interface Lifecycle {

    void startingScreen(Maps.ToDummy presenter);
    void resumingScreen(Maps.DummyTo presenter);

    void startingScreen(Home.ToDummy presenter);
    void resumingScreen(Home.DummyTo presenter);

    void startingScreen(Calendar.ToCalendar presenter);
    void resumingScreen(Calendar.CalendarTo presenter);

    void startingScreen(Chat.ToDummy presenter);
    void resumingScreen(Chat.DummyTo presenter);

    void startingScreen(Webshop.ToDummy presenter);
    void resumingScreen(Webshop.DummyTo presenter);
  }

  interface Navigation {

    void goToNextScreen(Maps.DummyTo presenter);
    void backToPreviousScreen(Maps.DummyTo presenter);

    void goToNextScreen(Home.DummyTo presenter);
    void backToPreviousScreen(Home.DummyTo presenter);

    void goToNextScreen(Calendar.CalendarTo presenter);
    void backToPreviousScreen(Calendar.CalendarTo presenter);

    void goToNextScreen(Chat.DummyTo presenter);
    void backToPreviousScreen(Chat.DummyTo presenter);

    void goToNextScreen(Webshop.DummyTo presenter);
    void backToPreviousScreen(Webshop.DummyTo presenter);
  }
}
