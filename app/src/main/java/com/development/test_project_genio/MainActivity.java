package com.development.test_project_genio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private List<Earthquake> earthquakeList;
    private static final String USGS_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        earthquakeList = new ArrayList<>();

        loadData();
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

                            adapter = new EarthquakeRecyclerViewAdapter(earthquakeList,getApplicationContext());
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


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

}
