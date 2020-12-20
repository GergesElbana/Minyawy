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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
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

     //   firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();




        placesListData=new ArrayList<>();
        clearData();
        getfirebasedata();

  }




private void getfirebasedata(){

    Query query=databaseReference.child("Restaurant").child("non");
    query.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            clearData();
            for(DataSnapshot data:snapshot.getChildren()){
             FetchPlaceName fetchPlaceName=new FetchPlaceName();
             fetchPlaceName.setName(data.child("name").getValue().toString());
                fetchPlaceName.setDescrip(data.child("descrip").getValue().toString());
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
