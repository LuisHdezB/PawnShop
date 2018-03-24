package es.ulpgc.eite.clean.mvp.sample.maps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.calendar.CalendarView;
import es.ulpgc.eite.clean.mvp.sample.chat.ChatView;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;

public class MapsView
    extends GenericActivity<Maps.PresenterToView, Maps.ViewToPresenter, MapsPresenter>
    implements Maps.PresenterToView {

  //private Toolbar toolbar;
  private Button button;
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);

    //toolbar = (Toolbar) findViewById(R.id.toolbar);
    //setSupportActionBar(toolbar);

    // Comento la línea de mi botón
    /*
    ImageButton mapsMenuView = (ImageButton) findViewById(R.id.m_maps);
    mapsMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(CalendarView.this, MapsView.class);
        startActivity(i);
      }
    });*/

    ImageButton shopMenuView = (ImageButton) findViewById(R.id.m_shop);
    shopMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(MapsView.this, WebshopView.class);
        startActivity(i);
      }
    });

    ImageButton calendarMenuView = (ImageButton) findViewById(R.id.m_calendar);
    calendarMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(MapsView.this, CalendarView.class);
        startActivity(i);
      }
    });

    ImageButton chatMenuView = (ImageButton) findViewById(R.id.m_chat);
    chatMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(MapsView.this, ChatView.class);
        startActivity(i);
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
    super.onResume(MapsPresenter.class, this);
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
    text.setVisibility(View.GONE);
  }

  @Override
  public void showText() {
    text.setVisibility(View.VISIBLE);
  }

  @Override
  public void setText(String txt) {
    text.setText(txt);
  }

  @Override
  public void setLabel(String txt) {  }
}