package com.example.minyawy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Places_Adapter places_adapter;
    Activity activity;

    @BindView(R.id.PlaceList)
    RecyclerView PlaceRecycler;
    Places_Adapter adapter;

    private Context context;
    private List<FechData> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        ButterKnife.bind(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Restaurant");

      /*  placesList=new ArrayList<>();
        placesList.add(new ItemPlaceModel("بندقة"," كشري ",R.drawable.download));
        placesList.add(new ItemPlaceModel("ست الشام","اكل سوري",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نيوكوليتي","ايس كريم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("بندقة"," كشري ",R.drawable.download));
        placesList.add(new ItemPlaceModel("ست الشام","اكل سوري",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نيوكوليتي","ايس كريم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("بندقة"," كشري ",R.drawable.download));
        placesList.add(new ItemPlaceModel("ست الشام","اكل سوري",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نيوكوليتي","ايس كريم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("بندقة"," كشري ",R.drawable.download));
        placesList.add(new ItemPlaceModel("ست الشام","اكل سوري",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نيوكوليتي","ايس كريم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("بندقة"," كشري ",R.drawable.download));
        placesList.add(new ItemPlaceModel("ست الشام","اكل سوري",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نيوكوليتي","ايس كريم",R.drawable.ger));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        PlaceRecycler.setLayoutManager(linearLayoutManager);
        Places_Adapter adapter =new Places_Adapter(placesList,this);
        PlaceRecycler.setAdapter(adapter);*/


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                placesList = new ArrayList<>();

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    FechData fechDataL = dataSnapshot1.getValue(FechData.class);
                    placesList.add(fechDataL);

                }
                places_adapter = new Places_Adapter(placesList, PlaceListActivity.this);
                PlaceRecycler.setAdapter(places_adapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }}
