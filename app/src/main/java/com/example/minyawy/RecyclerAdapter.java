package com.example.minyawy;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RestViewholder> {
    ArrayList<Place_Model> places=new ArrayList<>();
    Context context;
  //  TextView placeName;

    public RecyclerAdapter(ArrayList<Place_Model> places, Context context) {
        this.places = places;
        this.context=context;
    }

    @NonNull
    @Override

    public RestViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview,parent,false);

        RestViewholder restViewholder= new RestViewholder(view);
        return restViewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RestViewholder holder, int position) {

        holder.placeName.setText(places.get(position).getName());
        holder.placeLogo.setImageResource(places.get(position).getPhoto());
        holder.homcard.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                String placenametext=holder.placeName.getText().toString();
                if (placenametext.equals("Cafe"))
                {
                    Intent nona=new Intent(context,PlaceListActivity.class);
                    context.startActivity(nona);
                }




            }
        });
    }

    @Override
    public int getItemCount() {
       return places.size();

    }
    public void setdata(ArrayList<Place_Model>placemodle){
        this.places=placemodle;
        notifyDataSetChanged();

    }

    public class RestViewholder extends RecyclerView.ViewHolder {

        ImageView placeLogo;
        CardView homcard;
       TextView placeName;
        public RestViewholder(@NonNull View itemView) {
            super(itemView);
            homcard=(CardView)itemView.findViewById(R.id.HomeCard);
            placeName=(TextView)itemView.findViewById(R.id.RestNameText);
            placeLogo=(ImageView)itemView.findViewById(R.id.RestLogo);

        }
    }

}
