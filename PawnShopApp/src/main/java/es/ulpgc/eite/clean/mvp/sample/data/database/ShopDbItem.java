package es.ulpgc.eite.clean.mvp.sample.data.database;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.sample.app.Shop;
import io.realm.RealmObject;

/**
 * Created by Luis on 19/11/16.
 */

public class ShopDbItem extends RealmObject{

  private Integer id;
  private String name;
  private String code;
  private int zone;
  private String mail;
  private String latitude;
  private String longitude;


  public ShopDbItem() {
  }



  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getZone() {
    return zone;
  }

  public void setZone(int zone) {
    this.zone = zone;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getLatitude() { return latitude; }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public double getLatitudeD() { return Double.parseDouble(latitude); }

  public void setLatitudeD(double latitude) {
    this.latitude = Double.toString(latitude);
  }

  public String getLongitude() { return longitude; }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public double getLongitudeD() { return Double.parseDouble(longitude); }

  public void setLongitudeD(double longitude) {
    this.longitude = Double.toString(longitude);
  }



  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ShopDbItem){
      ShopDbItem item = (ShopDbItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
