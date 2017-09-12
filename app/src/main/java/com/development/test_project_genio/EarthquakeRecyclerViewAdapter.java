package com.development.test_project_genio;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Yevgeniy on 9/11/2017.
 */

public class EarthquakeRecyclerViewAdapter extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapter.ViewHolder> {


    private List<Earthquake> earthquakeList;

    private Context context;



    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakeList, Context context){
        this.earthquakeList = earthquakeList;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView magTextview;
        public final TextView locTextview;

        public ViewHolder(View itemView){
            super(itemView);

            magTextview = (TextView) itemView.findViewById(R.id.magnitude);
            locTextview = (TextView) itemView.findViewById(R.id.location);

        }

    }






    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Earthquake earthquake = earthquakeList.get(position);

        holder.magTextview.setText(formatMag(earthquake.getMagnitude()));
        holder.locTextview.setText(earthquake.getLocation());


    }


    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }


    private String formatMag(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }


}
