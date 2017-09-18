package com.development.test_project_genio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.development.test_project_genio.model.Earthquake;
import com.development.test_project_genio.realm.RealmHelper;
import com.development.test_project_genio.adapters.SavedQuakesRecyclerViewAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Yevgeniy on 9/12/2017.
 */

public class EarthquakeSaved extends Fragment{

    private RealmHelper realmHelper;
    private Realm realm;
    private ArrayList<Earthquake> earthquakeArrayList;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);

        Realm.init(getContext());


        View view = inflater.inflate(R.layout.saved_fragment,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_saved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        earthquakeArrayList = realmHelper.retrieve();

        adapter = new SavedQuakesRecyclerViewAdapter(earthquakeArrayList,getContext());
        recyclerView.setAdapter(adapter);

        return view;

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser && isResumed()){
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()){
            return;
        }


        realm = Realm.getDefaultInstance();

        realmHelper = new RealmHelper(realm);


        earthquakeArrayList = realmHelper.retrieve();

        adapter = new SavedQuakesRecyclerViewAdapter(earthquakeArrayList,getContext());
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
