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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Places_Adapter extends RecyclerView.Adapter<Places_Adapter.viwHolder> {
    private List<FetchPlaceName> fechDataList;
    private Activity activity;
    private Context context0;
    int lastPosition=-1;
    public static String placenamee;
    private Admin_SendData admin_sendData;
    public static String Id;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,fvrtref, fvrt_listRef;


    private Boolean fvrtCheker=false;

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

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String currentUser=user.getUid();

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");
        final String name=holder.placeName.getText().toString();
        final String pd=holder.placedesc.getText().toString();
        final String ph=fechDataList.get(position).getPhoto();
        final FetchPlaceName f=new FetchPlaceName(pd,ph,name);

        final String postKey=fechDataList.get(position).getName();
        holder.favoriteChecker(postKey);
        holder.fvrt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fvrtref=firebaseDatabase.getReference("favourites");
                fvrt_listRef=firebaseDatabase.getReference("favouriteList").child(currentUser);

                fvrtCheker=true;
                fvrtref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (fvrtCheker.equals(true)) {
                            if (snapshot.child(postKey).hasChild(currentUser)) {
                                fvrtref.child(postKey).child(currentUser).removeValue();
                                delete(name);
                                fvrtCheker = false;
                            } else {
                                fvrtref.child(postKey).child(currentUser).setValue(true);
                               FetchPlaceName fetchPlaceName = new FetchPlaceName();
                                fetchPlaceName.setName(name);
                                fetchPlaceName.setDescrip(pd);
                                fetchPlaceName.setPhoto(ph);

                                String id = fvrt_listRef.push().getKey();
                                fvrt_listRef.child(id).setValue(fetchPlaceName);
                                fvrtCheker = false;
                            }


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                

            }
        });





    }

    private void delete(String name) {
        Query query=fvrt_listRef.orderByChild("name").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    dataSnapshot.getRef().removeValue();
                    Toast.makeText(activity, "Deleted", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
        DatabaseReference favouriteRef;
        FirebaseDatabase database=FirebaseDatabase.getInstance();


        public viwHolder(@NonNull View itemView) {
            super(itemView);
            placeName=(TextView)itemView.findViewById(R.id.text_placeName);
            placedesc=(TextView)itemView.findViewById(R.id.text_placedescrip);
            placephoto=(ImageView)itemView.findViewById(R.id.itemImage);
            card=(CardView) itemView.findViewById(R.id.card0);
            fvrt_btn=(ImageButton)itemView.findViewById(R.id.favrt_btn) ;
        }

        public void favoriteChecker(final String postKey) {

            fvrt_btn=itemView.findViewById(R.id.favrt_btn);

            fvrtref=firebaseDatabase.getReference("favourites");
            FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            final String uid=user.getUid();

            fvrtref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.child(postKey).hasChild(uid))
                    {
                        fvrt_btn.setImageResource(R.drawable.ic_favorite_2);
                    }
                    else
                    {
                        fvrt_btn.setImageResource(R.drawable.ic_favorite);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}
