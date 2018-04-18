package es.ulpgc.eite.clean.mvp.sample.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import es.ulpgc.eite.clean.mvp.sample.calendar.CalendarView;
import es.ulpgc.eite.clean.mvp.sample.chat.ChatView;
import es.ulpgc.eite.clean.mvp.sample.home.HomePresenter;
import es.ulpgc.eite.clean.mvp.sample.home.HomeView;
import es.ulpgc.eite.clean.mvp.sample.webshop.WebshopView;

public class MapsView
    extends GenericActivity<Maps.PresenterToView, Maps.ViewToPresenter, MapsPresenter>
    implements Maps.PresenterToView  {


  private ImageButton menuImage;
  private MapView mapView;
  private GoogleMap map;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    Log.d(TAG, "calling onCreate()");


    // Menú inferior
    ImageButton chatMenuImage = (ImageButton) findViewById(R.id.m_chat);
    ImageButton calendarMenuImage = (ImageButton) findViewById(R.id.m_calendar);
    ImageButton webMenuImage = (ImageButton) findViewById(R.id.m_shop);
    menuImage = (ImageButton) findViewById(R.id.m_maps);

      //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //setSupportActionBar(toolbar);

     mapView = (MapView) findViewById(R.id.cm_mapview);

    mapView.onCreate(savedInstanceState);

    map = mapView.getMap();
    map.getUiSettings().setMyLocationButtonEnabled(false);
    map.getUiSettings().setMapToolbarEnabled(true);
    map.getUiSettings().setZoomControlsEnabled(true);
    map.getUiSettings().setZoomGesturesEnabled(true);
    //map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


    MapsInitializer.initialize(this);
    // Listeners del menú
    chatMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onChatButtonClicked();
      }
    });

    webMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onShopButtonClicked();
      }
    });
    //

    calendarMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onCalendarButtonClicked();
      }
    });

    /*SharedPreferences preferences = getApplicationContext().getSharedPreferences(HomePresenter.TIENDAS_PREFERENCES, Context.MODE_PRIVATE);
    String nombre = preferences.getString(HomePresenter.KEY_TIENDA_NOMBRE, "");
    Double latitud = ((Long) preferences.getLong(HomePresenter.KEY_LATITUD, 0)).doubleValue();
    Double longitud = ((Long) preferences.getLong(HomePresenter.KEY_LONGITUD, 0)).doubleValue();

    List<String> nombres = Arrays.asList(getResources().getStringArray(R.array.spinner));
    for (int i = 0; i < nombres.size(); i++) {
      LatLng latLng = new LatLng(GeoUtils.getLatitudFromPosition(i), GeoUtils.getLongitudFromPosition(i));
        Marker marker = map.addMarker(new MarkerOptions().position(latLng).title(nombres.get(i)));
    }

    LatLng latLng = new LatLng(latitud, longitud);
    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,10);
    map.animateCamera(cameraUpdate);*/
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(MapsPresenter.class, this);
    mapView.onResume();
    menuImage.setImageResource(R.drawable.ic_maps_icon_m);
    getPresenter().startLoadMarkerList();
  }

  @Override
  public void onBackPressed() {
    // super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
    Intent intent = new Intent(this, HomeView.class);
    startActivity(intent);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
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
    public void setMarkersToMap(ArrayList<Shop> mapShopList) {
      LatLng latLng;
      Marker marker;
        for (int i = 0; i < mapShopList.size(); i++) {
          latLng = new LatLng(mapShopList.get(i).getLatitude(), mapShopList.get(i).getLongitud());
          marker = map.addMarker(new MarkerOptions().position(latLng).title(mapShopList.get(i).getName()));
          Log.d(TAG, "setMarkersToMap: " + mapShopList.get(i).getLatitude() + "," + mapShopList.get(i).getLongitud());
        }
    }

  @Override
  public void setCenterCamera(Shop shop) {
    LatLng latLng = new LatLng(shop.getLatitude(), shop.getLongitud());
    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,10);
    map.animateCamera(cameraUpdate);
  }


}