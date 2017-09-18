package com.development.test_project_genio.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.development.test_project_genio.model.Earthquake;
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
    private RealmHelper realmHelper;



    public EarthquakeRecyclerViewAdapter(List<Earthquake> earthquakeList, Context context){
        this.earthquakeList = earthquakeList;
        this.context = context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final TextView magTextview;
        public final TextView locTextview;
        public final CardView cardView;
        public final ImageButton imageButton;

        public ViewHolder(View itemView){
            super(itemView);

            magTextview = (TextView) itemView.findViewById(R.id.magnitude);
            locTextview = (TextView) itemView.findViewById(R.id.location);
            cardView = (CardView) itemView.findViewById(R.id.mycardview);
            imageButton = (ImageButton) itemView.findViewById(R.id.saveButton);

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



        realm = Realm.getDefaultInstance();

        final Earthquake earthquake = earthquakeList.get(position);

        holder.magTextview.setText(formatMag(earthquake.getMagnitude()));
        holder.locTextview.setText(earthquake.getLocation());


        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realmHelper = new RealmHelper(realm);
                realmHelper.save(earthquake);
                Toast.makeText(context,"Earthquake saved",Toast.LENGTH_SHORT).show();
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
