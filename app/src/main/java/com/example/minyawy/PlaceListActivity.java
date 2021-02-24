package com.example.minyawy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.minyawy.Admin.Admin_SendData;
import com.example.minyawy.Admin.RestaurantDescriptionAdmin;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, photoData, fvrtref, fvrt_listRef;
    private Boolean fvrtCheker=false;
    private FetchPlaceName placeName;
    private Places_Adapter places_adapter;


    @BindView(R.id.PlaceList)
    RecyclerView PlaceRecycler;


    private Context context;
    private List<FetchPlaceName> placesListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        ButterKnife.bind(this);
      LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        PlaceRecycler.setLayoutManager(linearLayoutManager);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        String CurrentUser=user.getUid();
     //   firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        photoData=FirebaseDatabase.getInstance().getReference();
       // placeName=new FetchPlaceName();
//        fvrtref=firebaseDatabase.getReference("user").child("favourites");
//        fvrt_listRef=firebaseDatabase.getReference("user").child("favouriteList").child(CurrentUser);

        placesListData=new ArrayList<>();
        clearData();
        getfirebasedata();

       /* photoData.child("menu_photo").child(RestaurantDescriptionAdmin.string).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data:snapshot.getChildren()){
                    FetchPlaceName fetchPlaceName=new FetchPlaceName();
                   // fetchPlaceName.setPhoto_1(data.child("photo_1").getValue().toString());
                    Glide.with(PlaceListActivity.this).load(fetchPlaceName.getPhoto_1()).into();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


    }




private void getfirebasedata(){


    Query query=databaseReference.child( RecyclerAdapter.placenametext);
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            clearData();
            for(DataSnapshot data:snapshot.getChildren()){
             FetchPlaceName fetchPlaceName=new FetchPlaceName();
             fetchPlaceName.setName(data.child("name").getValue().toString());
                fetchPlaceName.setDescrip(data.child("descrip").getValue().toString());
                fetchPlaceName.setNumber(data.child("number").getValue().toString());
                fetchPlaceName.setLocation(data.child("location").getValue().toString());
                fetchPlaceName.setPhoto(data.child("photo").getValue().toString());
                placesListData.add(fetchPlaceName);

            }
            places_adapter = new Places_Adapter(placesListData, PlaceListActivity.this);
            PlaceRecycler.setAdapter(places_adapter);
            places_adapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

}
private void clearData(){
        if(placesListData!=null){
            placesListData.clear();
            if (places_adapter!=null){
                places_adapter.notifyDataSetChanged();


            }

        }

            placesListData=new ArrayList<>();





}

}
