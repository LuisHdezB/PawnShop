package es.ulpgc.eite.clean.mvp.sample.data;

import java.util.ArrayList;
import java.util.List;

public class DatabaseFacade implements Database {
    private ArrayList<ModelDbItem> data;
    private ArrayList<Boolean> validDataMap;

    private DatabaseFacade() {
        data = new ArrayList<ModelDbItem>();
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
    public void insertDatabaseItem(ModelDbItem item) {
        item.setId(data.size());

        data.add(item);
        validDataMap.add(true);
    }

    @Override
    public void deleteAllDatabaseItems(){
        for(ModelDbItem item: getAllItemsFromDatabase()){
            deleteDatabaseItem(item.getId());
        }
    }

    @Override
    public void deleteDatabaseItem(Integer id) {
        validDataMap.set(id, false);
    }

    @Override
    public ModelDbItem getItem(Integer id) {
        if (validDataMap.get(id)) {
            return data.get(id);
        } else {
            return null;
        }
    }

    @Override
    public List<ModelDbItem> getAllItemsFromDatabase(){
        ArrayList<ModelDbItem> result = new ArrayList<ModelDbItem>();

        for (int id=0; id<validDataMap.size(); id++) {
            if (validDataMap.get(id)) {
                result.add(data.get(id));
            }
        }

        return result;
    }

    @Override
    public ModelDbItem[] getAllItemsArrayFromDatabase(){
        int length = 0;

        // Compute the length of the returned array
        for (int id=0; id<validDataMap.size(); id++) {
            if (validDataMap.get(id)) {
                length++;
            }
        }

        // Build the array and fill the results

        ModelDbItem[] result = new ModelDbItem[length];
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
