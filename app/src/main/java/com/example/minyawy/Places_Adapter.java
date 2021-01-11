package com.example.minyawy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minyawy.Admin.Admin_SendData;
import com.example.minyawy.Admin.RestaurantDescriptionAdmin;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class Places_Adapter extends RecyclerView.Adapter<Places_Adapter.viwHolder> {
    List<FetchPlaceName> fechDataList;
    Activity activity;
   Context context0;
   int lastPosition=-1;
   public static String placenamee;
   Admin_SendData admin_sendData;
   public static String Id;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


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
    public void onBindViewHolder(@NonNull final viwHolder holder, final int position) {

      /*  viwHolder viwHolder=(viwHolder)holder;
        FechData fechData=fechDataList.get(position);
        viwHolder.placeName.setText(fechData.getPlaceName());
        viwHolder.placedesc.setText(fechData.location);*/
        holder.placeName.getText().toString();
        holder.placeName.setText(fechDataList.get(position).getName());
        holder.placedesc.setText(fechDataList.get(position).getDescrip());
       Glide.with(activity)
               .load(fechDataList.get(position)
                       .getPhoto()).into(holder.placephoto);



        setAnimation(holder.card,position);
       holder.placephoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


              /* Intent ih=new Intent(activity,RestaurantDescription.class);
               activity.startActivity(ih);*/
                Intent intent=new Intent(activity,RestaurantDescription.class);
                intent.putExtra("name",fechDataList.get(position).getName());
                intent.putExtra("dis",fechDataList.get(position).getDescrip());
                intent.putExtra("photo",fechDataList.get(position).getPhoto());
                intent.putExtra("num",fechDataList.get(position).getNumber());
                intent.putExtra("loc",fechDataList.get(position).getLocation());
                activity.startActivity(intent);




            }
        });

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity,RestaurantDescription.class);
                intent.putExtra("name",fechDataList.get(position).getName());
                intent.putExtra("dis",fechDataList.get(position).getDescrip());
                intent.putExtra("photo",fechDataList.get(position).getPhoto());
                intent.putExtra("num",fechDataList.get(position).getNumber());
                intent.putExtra("loc",fechDataList.get(position).getLocation());
                activity.startActivity(intent);

            }
        });*/

        holder.fvrt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseDatabase=FirebaseDatabase.getInstance();
                databaseReference=firebaseDatabase.getReference("user");
                String pn=holder.placeName.getText().toString();
                String pd=holder.placedesc.getText().toString();
                String ph=fechDataList.get(position).getPhoto();
                FetchPlaceName f=new FetchPlaceName(ph,pn,pd);
           //.child(Register_Activity.id)
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                currentFirebaseUser.getUid();
                String m= currentFirebaseUser.getUid();
                databaseReference.

                        child("favoriteList").child(m).push()
                        .setValue(f)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                

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
        ImageButton fvrt_btn;


        public viwHolder(@NonNull View itemView) {
            super(itemView);
            placeName=(TextView)itemView.findViewById(R.id.text_placeName);
            placedesc=(TextView)itemView.findViewById(R.id.text_placedescrip);
            placephoto=(ImageView)itemView.findViewById(R.id.itemImage);
            card=(CardView) itemView.findViewById(R.id.card0);
            fvrt_btn=(ImageButton)itemView.findViewById(R.id.favrt_btn) ;
        }
    }
}
