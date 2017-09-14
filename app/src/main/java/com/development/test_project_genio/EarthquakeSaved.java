package com.development.test_project_genio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.development.test_project_genio.realm.RealmHelper;
import com.development.test_project_genio.realm.SavedQuakesRecyclerViewAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Yevgeniy on 9/12/2017.
 */

public class EarthquakeSaved extends Fragment{

    private RealmHelper realmHelper;
    private Realm realm;
    private RealmConfiguration realmConfiguration;
    private ArrayList<Earthquake> earthquakeArrayList;
    private SavedQuakesRecyclerViewAdapter savedQuakesRecyclerViewAdapter;
    private RecyclerView recyclerView;





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);


        View view = inflater.inflate(R.layout.saved_fragment,container,false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_saved);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));


        realmConfiguration = new RealmConfiguration.Builder(getContext()).build();
        realm = Realm.getInstance(realmConfiguration);

        realmHelper = new RealmHelper(realm);


        earthquakeArrayList = realmHelper.retrieve();

        savedQuakesRecyclerViewAdapter = new SavedQuakesRecyclerViewAdapter(earthquakeArrayList,getContext());
        recyclerView.setAdapter(savedQuakesRecyclerViewAdapter);



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
        Toast.makeText(getContext(),"hi hello", Toast.LENGTH_SHORT).show();
        realmConfiguration = new RealmConfiguration.Builder(getContext()).build();
        realm = Realm.getInstance(realmConfiguration);

        realmHelper = new RealmHelper(realm);


        earthquakeArrayList = realmHelper.retrieve();

        savedQuakesRecyclerViewAdapter = new SavedQuakesRecyclerViewAdapter(earthquakeArrayList,getContext());
        recyclerView.setAdapter(savedQuakesRecyclerViewAdapter);


    }
}
