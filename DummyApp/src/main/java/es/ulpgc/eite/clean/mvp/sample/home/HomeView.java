package es.ulpgc.eite.clean.mvp.sample.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.maps.MapsView;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;

public class HomeView
    extends GenericActivity<Home.PresenterToView, Home.ViewToPresenter, HomePresenter>
    implements Home.PresenterToView {

  private Toolbar toolbar;
  private Button button;
  private TextView text;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    RelativeLayout mapsMenuView = (RelativeLayout) findViewById(R.id.m_maps);
    mapsMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(HomeView.this, MapsView.class);
        startActivity(i);
      }
    });

    RelativeLayout shopMenuView = (RelativeLayout) findViewById(R.id.m_shop);
    shopMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(HomeView.this, WebshopView.class);
        startActivity(i);
      }
    });
    RelativeLayout calendarMenuView = (RelativeLayout) findViewById(R.id.m_calendar);
    calendarMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(HomeView.this, WebshopView.class);
        startActivity(i);
      }
    });
    RelativeLayout chatMenuView = (RelativeLayout) findViewById(R.id.m_chat);
    chatMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(HomeView.this, WebshopView.class);
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
    super.onResume(HomePresenter.class, this);
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
    toolbar.setVisibility(View.GONE);
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
  public void setLabel(String txt) {

  }
}
