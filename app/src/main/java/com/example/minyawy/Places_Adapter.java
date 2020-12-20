package com.example.minyawy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Places_Adapter extends RecyclerView.Adapter<Places_Adapter.viwHolder> {
    List<FetchPlaceName> fechDataList;
    Activity activity;
   Context context0;
   int lastPosition=-1;

    public Places_Adapter(List<FetchPlaceName> fechDataList ,Activity activity) {
        this.fechDataList = fechDataList;
        this.activity=activity;
    }

    @NonNull
    @Override
    public viwHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_list,parent,false);
        viwHolder viwHolder=new viwHolder(view);
        return viwHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viwHolder holder, final int position) {

      /*  viwHolder viwHolder=(viwHolder)holder;
        FechData fechData=fechDataList.get(position);
        viwHolder.placeName.setText(fechData.getPlaceName());
        viwHolder.placedesc.setText(fechData.location);*/
        holder.placeName.setText(fechDataList.get(position).getName());
        holder.placedesc.setText(fechDataList.get(position).getDescrip());
       Glide.with(activity)
               .load(fechDataList.get(position)
                       .getPhoto()).into(holder.placephoto);



        setAnimation(holder.card,position);
       holder.card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent ih=new Intent(activity,RestaurantDescription.class);
               activity.startActivity(ih);

            }
        });


    }

    private void setAnimation(CardView card, int position) {
        if (position>lastPosition){


            Animation animation= AnimationUtils.loadAnimation(activity,android.R.anim.slide_in_left);
            card.setAnimation(animation);
            lastPosition=position;
        }

    }

    @Override
    public int getItemCount() {
        return fechDataList.size();
    }

    public class viwHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placedesc;
        ImageView placephoto;
        CardView card;

        public viwHolder(@NonNull View itemView) {
            super(itemView);
            placeName=(TextView)itemView.findViewById(R.id.text_placeName);
            placedesc=(TextView)itemView.findViewById(R.id.text_placedescrip);
            placephoto=(ImageView)itemView.findViewById(R.id.itemImage);
            card=(CardView) itemView.findViewById(R.id.card0);
        }
    }
}
