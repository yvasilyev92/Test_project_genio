package com.development.test_project_genio.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.development.test_project_genio.Earthquake;
import com.development.test_project_genio.R;
import com.development.test_project_genio.realm.RealmHelper;

import java.text.DecimalFormat;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Yevgeniy on 9/11/2017.
 */

public class EarthquakeRecyclerViewAdapter extends RecyclerView.Adapter<EarthquakeRecyclerViewAdapter.ViewHolder> {


    private List<Earthquake> earthquakeList;

    private Context context;

    private Realm realm;
    private RealmConfiguration realmConfig;
    private RealmHelper realmHelper;



    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakeList, Context context){
        this.earthquakeList = earthquakeList;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView magTextview;
        public final TextView locTextview;
        public final CardView cardView;

        public ViewHolder(View itemView){
            super(itemView);

            magTextview = (TextView) itemView.findViewById(R.id.magnitude);
            locTextview = (TextView) itemView.findViewById(R.id.location);
            cardView = (CardView) itemView.findViewById(R.id.mycardview);

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


        realmConfig = new RealmConfiguration.Builder(context).build();
        realm = Realm.getInstance(realmConfig);

        final Earthquake earthquake = earthquakeList.get(position);

        holder.magTextview.setText(formatMag(earthquake.getMagnitude()));
        holder.locTextview.setText(earthquake.getLocation());


        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                realmHelper = new RealmHelper(realm);
                realmHelper.save(earthquake);
                Toast.makeText(context,"Earthquake saved",Toast.LENGTH_SHORT).show();
                return false;
            }
        });






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
