package es.ulpgc.eite.clean.mvp.sample.data;


import es.ulpgc.eite.clean.mvp.sample.data.database.ShopDbItem;

public class ShopItem {

  private ShopDbItem dbItem;

  //  Disabling the default constructor
  private ShopItem() {

  }

  public ShopItem(ShopDbItem dbItem) {
    this.dbItem = dbItem;
  }

  public String getContent() {
    return dbItem.getName();
  }

  public ShopDbItem getDbItem() {
    return dbItem;
  }

  public String getDetails() {
    String mail = dbItem.getMail();
    String nombre = dbItem.getName();

    return "Nombre: " + nombre + "\n" + "Mail: " + mail + "\n";
  }

  public String getId() {
    return dbItem.getId().toString();
  }

  @Override
  public String toString() {
    return this.getContent();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ShopItem){
      ShopItem item = (ShopItem) obj;
      if(item.getId() == getId()){
        return true;
      }
    }
    return false;
  }
}
