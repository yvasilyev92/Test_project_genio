package com.development.test_project_genio.realm;

import com.development.test_project_genio.Earthquake;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Yevgeniy on 9/12/2017.
 */

public class RealmHelper {

    Realm realm;


    public RealmHelper(Realm realm){
        this.realm = realm;
    }



    public void save(final Earthquake earthquake){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Earthquake earthquake1  = realm.copyToRealm(earthquake);
            }
        });

    }


    public ArrayList<Earthquake> retrieve(){

        ArrayList<Earthquake> savedEarthquakes = new ArrayList<>();
        RealmResults<Earthquake> earthquakes = realm.where(Earthquake.class).findAll();

        for (Earthquake e: earthquakes){
            savedEarthquakes.add(e);
        }

        return savedEarthquakes;

    }



}
