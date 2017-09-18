package com.development.test_project_genio.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Yevgeniy on 9/17/2017.
 */

public class ConfigRealm extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("earthquake.realm")
                .build();


        Realm.setDefaultConfiguration(realmConfiguration);



    }
}
