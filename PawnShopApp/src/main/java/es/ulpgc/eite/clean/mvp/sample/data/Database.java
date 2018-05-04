package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.sample.app.Shop;

interface Database {
    // Methods to add information to the database
    void insertDatabaseItem(Shop item);

    // Methods to get information from the database
    Shop getItem(Integer id);

    ArrayList<Shop> getAllItemsFromDatabase();
    Shop[] getAllItemsArrayFromDatabase();

    // Methods to remove information from the database
    void deleteDatabaseItem(Integer id);
    void deleteAllDatabaseItems();
}
