package com.development.test_project_genio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.development.test_project_genio.adapters.EarthquakeRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevgeniy on 9/12/2017.
 */

public class EarthquakeResults extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Earthquake> earthquakeList;
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater,container,savedInstanceState);


        View view = inflater.inflate(R.layout.results_fragment,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_results);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setHasFixedSize(true);

        earthquakeList = new ArrayList<>();

        loadData();

        return view;

    }


    private void loadData(){


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                USGS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject baseJsonResponse = new JSONObject(response);
                            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("features");

                            for (int i = 0; i < earthquakeArray.length(); i++){

                                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                                JSONObject properties = currentEarthquake.getJSONObject("properties");

                                double magnitude = properties.getDouble("mag");
                                String location = properties.getString("place");

                                Earthquake earthquake = new Earthquake(magnitude,location);
                                earthquakeList.add(earthquake);
                            }

                            adapter = new EarthquakeRecyclerViewAdapter(earthquakeList,getContext());
                            recyclerView.setAdapter(adapter);




                        } catch (JSONException e){
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }



}
