package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.calendar.Calendar;
import es.ulpgc.eite.clean.mvp.sample.chat.Chat;
import es.ulpgc.eite.clean.mvp.sample.home.Home;
import es.ulpgc.eite.clean.mvp.sample.maps.Maps;
import es.ulpgc.eite.clean.mvp.sample.webshop.Webshop;


public interface Mediator {

  interface Lifecycle {

    void startingScreen(Maps.ToMaps presenter);
    void resumingScreen(Maps.MapsTo presenter);

    void startingScreen(Home.ToHome presenter);
    void resumingScreen(Home.HomeTo presenter);

    void startingScreen(Calendar.ToCalendar presenter);
    void resumingScreen(Calendar.CalendarTo presenter);

    void startingScreen(Chat.ToDummy presenter);
    void resumingScreen(Chat.DummyTo presenter);

    void startingScreen(Webshop.ToDummy presenter);
    void resumingScreen(Webshop.DummyTo presenter);
  }

  interface Navigation {

    void goToNextScreen(Maps.MapsTo presenter);
    void backToPreviousScreen(Maps.MapsTo presenter);

    void goToNextScreen(Home.HomeTo presenter);
    void backToPreviousScreen(Home.HomeTo presenter);

    void goToNextScreen(Calendar.CalendarTo presenter);
    void backToPreviousScreen(Calendar.CalendarTo presenter);

    void goToNextScreen(Chat.DummyTo presenter);
    void backToPreviousScreen(Chat.DummyTo presenter);

    void goToNextScreen(Webshop.DummyTo presenter);
    void backToPreviousScreen(Webshop.DummyTo presenter);
  }
}
