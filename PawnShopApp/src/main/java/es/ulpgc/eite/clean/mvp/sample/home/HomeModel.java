package es.ulpgc.eite.clean.mvp.sample.home;

import android.util.Log;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericModel;
import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import es.ulpgc.eite.clean.mvp.sample.data.DatabaseFacade;


public class HomeModel
    extends GenericModel<Home.ModelToPresenter> implements Home.PresenterToModel {

  private DatabaseFacade db;
  /**
   * Method that recovers a reference to the PRESENTER
   * You must ALWAYS call {@link super#onCreate(Object)} here
   *
   * @param presenter Presenter interface
   */
  @Override
  public void onCreate(Home.ModelToPresenter presenter) {
    super.onCreate(presenter);
    Log.d(TAG, "calling onCreate()");
    db = DatabaseFacade.getInstance();
    getData();
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
  public void loadShopList() {
    Log.d(TAG, "calling loadShopList()");
    ArrayList<Shop> shopList = db.getAllItemsFromDatabase();
    ArrayList<String> nameShopList = new ArrayList<>();
    if (shopList.size() > 0){
      for(int i = 0; i < shopList.size(); i++){
        nameShopList.add(shopList.get(i).getName());
      }
    } else {
      nameShopList.add("No hay tiendas.");
    }
    getPresenter().setShopList(nameShopList);
  }

  @Override
  public Shop getShop(int position) {
    return db.getItem(position);
  }

  ///////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////////////

  private void getData(){
    Log.d(TAG, "calling getData()");

    Shop item;
    Shop.Timetable hour;
    ArrayList<Shop.Timetable> cal = new ArrayList<>();
    ArrayList<Shop.Timetable> cal2 = new ArrayList<>();

    // Two times timetable
    hour = new Shop.Timetable(0,"10:00-10:30");
    cal.add(hour);
    hour = new Shop.Timetable(1,"10:30-11:00");
    cal.add(hour);
    hour = new Shop.Timetable(2,"11:00-11:30");
    cal.add(hour);
    hour = new Shop.Timetable(3,"11:30-12:00");
    cal.add(hour);
    hour = new Shop.Timetable(4,"12:00-12:30");
    cal.add(hour);
    hour = new Shop.Timetable(5,"12:30-13:00");
    cal.add(hour);
    hour = new Shop.Timetable(6,"13:00-13:30");
    cal.add(hour);
    hour = new Shop.Timetable(7,"16:30-17:00");
    cal.add(hour);
    hour = new Shop.Timetable(8,"17:00-17:30");
    cal.add(hour);
    hour = new Shop.Timetable(9,"17:30-18:00");
    cal.add(hour);
    hour = new Shop.Timetable(10,"18:00-18:30");
    cal.add(hour);
    hour = new Shop.Timetable(11,"18:30-19:00");
    cal.add(hour);
    hour = new Shop.Timetable(12,"19:00-19:30");
    cal.add(hour);

    // One time timetable
    hour = new Shop.Timetable(0,"10:00-10:30");
    cal2.add(hour);
    hour = new Shop.Timetable(1,"10:30-11:00");
    cal2.add(hour);
    hour = new Shop.Timetable(2,"11:00-11:30");
    cal2.add(hour);
    hour = new Shop.Timetable(3,"11:30-12:00");
    cal2.add(hour);
    hour = new Shop.Timetable(4,"12:00-12:30");
    cal2.add(hour);
    hour = new Shop.Timetable(5,"12:30-13:00");
    cal2.add(hour);
    hour = new Shop.Timetable(6,"13:00-13:30");
    cal2.add(hour);
    hour = new Shop.Timetable(7,"13:30-14:00");
    cal2.add(hour);
    hour = new Shop.Timetable(8,"14:00-14:30");
    cal2.add(hour);
    hour = new Shop.Timetable(9,"14:30-15:00");
    cal2.add(hour);
    hour = new Shop.Timetable(10,"15:00-15:30");
    cal2.add(hour);
    hour = new Shop.Timetable(11,"15:30-16:00");
    cal2.add(hour);
    hour = new Shop.Timetable(12,"16:00-16:30");
    cal2.add(hour);
    hour = new Shop.Timetable(13,"16:30-17:00");
    cal2.add(hour);
    hour = new Shop.Timetable(14,"17:00-17:30");
    cal2.add(hour);
    hour = new Shop.Timetable(15,"17:30-18:00");
    cal2.add(hour);
    hour = new Shop.Timetable(16,"18:00-18:30");
    cal2.add(hour);
    hour = new Shop.Timetable(17,"18:30-19:00");
    cal2.add(hour);
    hour = new Shop.Timetable(18,"19:00-19:30");
    cal2.add(hour);
    hour = new Shop.Timetable(19,"19:30-20:00");
    cal2.add(hour);
    hour = new Shop.Timetable(20,"20:00-20:30");
    cal2.add(hour);
    hour = new Shop.Timetable(21,"20:30-21:00");
    cal2.add(hour);
    hour = new Shop.Timetable(22,"21:00-21:30");
    cal2.add(hour);

    double latitude;
    double longitud;
    // Mas de Gaminde: 28.1334703,-15.503952
    latitude = 28.1334307;
    longitud = -15.4339218;
    item = new Shop("General mas de Gaminde","0047",0,"0047@nocheydia.es", cal, latitude, longitud);
    db.insertDatabaseItem(item);
    // Vecindario: 27.8437632,-15.5138459
    latitude = 27.8438423;
    longitud = -15.4437497;
    item = new Shop("Vecindario","0053",0,"0053@nocheydia.es", cal, latitude,  longitud);
    db.insertDatabaseItem(item);
    // La Ballena: 28.0327196,-15.4739508
    latitude = 28.1015129;
    longitud = -15.4424514;
    item = new Shop("La Ballena","0075",0,"0075@nocheydia.es", cal2, latitude, longitud);
    db.insertDatabaseItem(item);
    // Triana 28.0329871,-15.473951
    latitude = 28.1055502;
    longitud = -15.4153973;
    item = new Shop("Triana","0089",0,"0089@nocheydia.es", cal,  latitude, longitud);
    db.insertDatabaseItem(item);
  }

}
