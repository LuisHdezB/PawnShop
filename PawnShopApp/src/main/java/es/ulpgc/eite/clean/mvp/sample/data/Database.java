package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.List;

interface Database {
    // Methods to add information to the database
    void insertDatabaseItem(ModelDbItem item);

    // Methods to get information from the database
    ModelDbItem getItem(Integer id);

    List<ModelDbItem> getAllItemsFromDatabase();
    ModelDbItem[] getAllItemsArrayFromDatabase();

    // Methods to remove information from the database
    void deleteDatabaseItem(Integer id);
    void deleteAllDatabaseItems();
}
