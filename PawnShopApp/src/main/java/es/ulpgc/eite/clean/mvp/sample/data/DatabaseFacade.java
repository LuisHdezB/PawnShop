package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.sample.app.Shop;

public class DatabaseFacade implements Database {
    private ArrayList<Shop> data;
    private ArrayList<Boolean> validDataMap;

    private DatabaseFacade() {
        data = new ArrayList<Shop>();
        validDataMap = new ArrayList<Boolean>();
    }

    private static DatabaseFacade instance;
    public static DatabaseFacade getInstance() {
        if (instance == null) {
            instance = new DatabaseFacade();
        }
        return instance;
    }

    @Override
    public void insertDatabaseItem(Shop item) {
        item.setId(data.size());

        data.add(item);
        validDataMap.add(true);
    }

    @Override
    public void deleteAllDatabaseItems(){
        for(Shop item: getAllItemsFromDatabase()){
            deleteDatabaseItem(item.getId());
        }
    }

    @Override
    public void deleteDatabaseItem(Integer id) {
        validDataMap.set(id, false);
    }

    @Override
    public Shop getItem(Integer id) {
        if (validDataMap.get(id)) {
            return data.get(id);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Shop> getAllItemsFromDatabase(){
        ArrayList<Shop> result = new ArrayList<Shop>();

        for (int id=0; id<validDataMap.size(); id++) {
            if (validDataMap.get(id)) {
                result.add(data.get(id));
            }
        }

        return result;
    }

    @Override
    public Shop[] getAllItemsArrayFromDatabase(){
        int length = 0;

        // Compute the length of the returned array
        for (int id=0; id<validDataMap.size(); id++) {
            if (validDataMap.get(id)) {
                length++;
            }
        }

        // Build the array and fill the results

        Shop[] result = new Shop[length];
        int pos = 0;
        for (int id=0; id<validDataMap.size(); id++) {
            if (validDataMap.get(id)) {
                result[pos] = data.get(id);
                pos++;
            }
        }

        return result;
    }

}
