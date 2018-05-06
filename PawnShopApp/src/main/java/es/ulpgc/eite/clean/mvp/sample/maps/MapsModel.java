package es.ulpgc.eite.clean.mvp.sample.maps;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import es.ulpgc.eite.clean.mvp.sample.data.DatabaseFacade;


public class MapsModel
    extends GenericModel<Maps.ModelToPresenter> implements Maps.PresenterToModel {


  private Shop shop;
  //private DatabaseFacade db;
  ArrayList<Shop> shopList;
  DatabaseReference connection;
  FirebaseDatabase database;

  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Maps.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");

    Log.d(TAG, "onCreate: Conectando con la BBDD");
    // Conectar con la BBDD
    database = FirebaseDatabase.getInstance();
    // Generar una referencia con la que conectar.
    connection = database.getReference();
    //db = DatabaseFacade.getInstance();
  }

  /**
   * Called by layer PRESENTER when VIEW pass for a reconstruction/destruction.
   * Usefull for kill/stop activities that could be running on the background Threads
   *
   * @param isChangingConfiguration Informs that a change is occurring on the configuration
   */
  @Override
  public void onDestroy(boolean isChangingConfiguration) {

  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To Model ////////////////////////////////////////////////////////////

  @Override
  public void loadMapMarker() {
    Log.d(TAG, "calling loadMapMarker()");

    DatabaseReference myRef = connection;
    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {

        GenericTypeIndicator<ArrayList<Shop>> indicator = new GenericTypeIndicator<ArrayList<Shop>>() {
        };
        shopList = dataSnapshot.getValue(indicator);
        /*
        Shop item;
        ArrayList<Shop> mapShopList = new ArrayList<>();
        if (shopList.size() > 0) {
          for (int i = 0; i < shopList.size(); i++) {
            item = new Shop(shopList.get(i).getName(), shopList.get(i).getLatitude(), shopList.get(i).getLongitude());
            mapShopList.add(item);
            Log.d(TAG, "onDataChange: Latitud: " + mapShopList.get(i).getLatitude());
          }
        } else {
          item = new Shop("No hay tiendas.", 0, 0);
          mapShopList.add(item);
        }*/
        getPresenter().setMarkerList(shopList);
      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
        Log.d(TAG, "Error leyendo la BBDD. " + databaseError.toException());
      }
    });
    /*
    ArrayList<Shop> shopList = db.getAllItemsFromDatabase();
    ArrayList<Shop> mapShopList = new ArrayList<>();
    Shop item;
    if (shopList.size() > 0){
      for(int i = 0; i < shopList.size(); i++){
        item = new Shop (shopList.get(i).getName(),shopList.get(i).getLatitude(),shopList.get(i).getLongitude());
        mapShopList.add(item);
      }
    } else {
      item = new Shop ("No hay tiendas.",0,0);
      mapShopList.add(item);
    }
    getPresenter().setMarkerList(mapShopList);
  }
  */
  }
}
