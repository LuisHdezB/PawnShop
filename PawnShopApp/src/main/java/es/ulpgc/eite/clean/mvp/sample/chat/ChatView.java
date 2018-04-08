package es.ulpgc.eite.clean.mvp.sample.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.calendar.CalendarView;
import es.ulpgc.eite.clean.mvp.sample.maps.MapsView;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;

public class ChatView
    extends GenericActivity<Chat.PresenterToView, Chat.ViewToPresenter, ChatPresenter>
    implements Chat.PresenterToView {


  private ImageButton menuImage;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    Log.d(TAG, "calling onCreate()");

    // Menú inferior
    ImageButton mapsMenuImage = (ImageButton) findViewById(R.id.m_maps);
    ImageButton calendarMenuImage = (ImageButton) findViewById(R.id.m_calendar);
    ImageButton webMenuImage = (ImageButton) findViewById(R.id.m_shop);
    menuImage = (ImageButton) findViewById(R.id.m_chat);

    // Listeners del menú
    mapsMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onMapsButtonClicked();
      }
    });

    webMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onShopButtonClicked();
      }
    });

    calendarMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       getPresenter().onCalendarButtonClicked();
      }
    });

  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(ChatPresenter.class, this);
    menuImage.setImageResource(R.drawable.ic_chat_icon_m);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

}
