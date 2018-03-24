package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.chat.ChatView;
import es.ulpgc.eite.clean.mvp.sample.maps.MapsView;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;

public class CalendarView
    extends GenericActivity<Calendar.PresenterToView, Calendar.ViewToPresenter, CalendarPresenter>
    implements Calendar.PresenterToView {

  //private Toolbar toolbar;
  private ImageButton menuImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calendar);
    Log.d(TAG, "calling onCreate()");

    //toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    ImageButton mapsMenuImage = (ImageButton) findViewById(R.id.m_maps);
    ImageButton chatMenuImage = (ImageButton) findViewById(R.id.m_chat);
    ImageButton webMenuImage = (ImageButton) findViewById(R.id.m_shop);
    menuImage = (ImageButton) findViewById(R.id.m_calendar);

    mapsMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(CalendarView.this, MapsView.class);
        startActivity(i);
      }
    });
    chatMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(CalendarView.this, ChatView.class);
        startActivity(i);
      }
    });
    webMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(CalendarView.this, WebshopView.class);
        startActivity(i);
      }
    });

    //De la antigua forma:
/*    RelativeLayout mapsMenuView = (RelativeLayout) findViewById(R.id.m_maps);
    mapsMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(CalendarView.this, MapsView.class);
        startActivity(i);
      }
    });

    RelativeLayout shopMenuView = (RelativeLayout) findViewById(R.id.m_shop);
    shopMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(CalendarView.this, WebshopView.class);
        startActivity(i);
      }
    });

    // Comento la línea de mi botón
    *//*
    RelativeLayout calendarMenuView = (RelativeLayout) findViewById(R.id.m_calendar);
    calendarMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(CalendarView.this, CalendarView.class);
        startActivity(i);
      }
    });
    *//*
    RelativeLayout chatMenuView = (RelativeLayout) findViewById(R.id.m_chat);
    chatMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(CalendarView.this, ChatView.class);
        startActivity(i);
      }
    });*/


  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(CalendarPresenter.class, this);
    menuImage.setImageResource(R.drawable.calendar_icon_r);
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

  @Override
  public void hideToolbar() {
    //toolbar.setVisibility(View.GONE);
  }

  @Override
  public void hideText() {

  }

  @Override
  public void showText() {

  }

  @Override
  public void setText(String txt) {
  }

  @Override
  public void setLabel(String txt) { }
}