package com.development.test_project_genio.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.development.test_project_genio.Earthquake;
import com.development.test_project_genio.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Yevgeniy on 9/13/2017.
 */


public class SavedQuakesRecyclerViewAdapter extends RecyclerView.Adapter<SavedQuakesRecyclerViewAdapter.ViewHolder> {


    private List<Earthquake> earthquakeList;

    private Context context;





    public SavedQuakesRecyclerViewAdapter(List<Earthquake> earthquakeList, Context context){
        this.earthquakeList = earthquakeList;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView magTextview;
        public final TextView locTextview;
        public final CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);

            magTextview = (TextView) itemView.findViewById(R.id.magnitude_saved);
            locTextview = (TextView) itemView.findViewById(R.id.location_saved);
            cardView = (CardView) itemView.findViewById(R.id.mycardviewsaved);

        }

    }






    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_saved,parent,false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        final Earthquake earthquake = earthquakeList.get(position);

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