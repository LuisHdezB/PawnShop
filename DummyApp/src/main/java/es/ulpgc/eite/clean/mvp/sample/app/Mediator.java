package es.ulpgc.eite.clean.mvp.sample.app;

import es.ulpgc.eite.clean.mvp.sample.dummy.Dummy;

/**
 * Created by imac on 23/1/18.
 */

public interface Mediator {

  interface Lifecycle {
    void startingScreen(Dummy.ToDummy presenter);
    void resumingScreen(Dummy.DummyTo presenter);
  }

  interface Navigation {
    void goToNextScreen(Dummy.DummyTo presenter);
    void backToPreviousScreen(Dummy.DummyTo presenter);
  }
}
