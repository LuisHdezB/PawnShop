package es.ulpgc.eite.clean.mvp.sample.webshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.calendar.CalendarView;
import es.ulpgc.eite.clean.mvp.sample.chat.ChatView;
import es.ulpgc.eite.clean.mvp.sample.maps.MapsView;

public class WebshopView
    extends GenericActivity<Webshop.PresenterToView, Webshop.ViewToPresenter, WebshopPresenter>
    implements Webshop.PresenterToView {

  private Toolbar toolbar;
  private Button button;
  private TextView text;
  private WebView webView_shop;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shop);
    Log.d(TAG, "calling onCreate()");

    text = (TextView) findViewById(R.id.text);

    //WEBVIEW
    webView_shop = (WebView) findViewById(R.id.webview_shop);
    WebSettings webSettings = webView_shop.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webView_shop.loadUrl("https://canarias.cashconverters.es/");
    webView_shop.setWebViewClient(new WebViewClient());

    //TOOLBAR
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    RelativeLayout mapsMenuView = (RelativeLayout) findViewById(R.id.m_maps);
    mapsMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(WebshopView.this, MapsView.class);
        startActivity(i);
      }
    });
    // Comento la línea de mi botón
    /*
    RelativeLayout shopMenuView = (RelativeLayout) findViewById(R.id.m_shop);
    shopMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(CalendarView.this, WebshopView.class);
        startActivity(i);
      }
    });*/

    RelativeLayout calendarMenuView = (RelativeLayout) findViewById(R.id.m_calendar);
    calendarMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(WebshopView.this, CalendarView.class);
        startActivity(i);
      }
    });

    RelativeLayout chatMenuView = (RelativeLayout) findViewById(R.id.m_chat);
    chatMenuView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(WebshopView.this, ChatView.class);
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
    super.onResume(WebshopPresenter.class, this);
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
    //button.setText(txt);
  }
}
