package com.example.minyawy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
RecyclerView recyclerView;
Context context;
    ArrayList<Place_Model> place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView=findViewById(R.id.PlaceRecycler);

        LinearLayoutManager LayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(LayoutManager);
        RecyclerAdapter adapter =new RecyclerAdapter(place,context);
        recyclerView.setAdapter(adapter);
        //initdata();
        ArrayList<Place_Model> place =new ArrayList<>();

        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));
        place.add(new Place_Model("Restaurant",R.drawable.rest));
        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));
        place.add(new Place_Model("Restaurant",R.drawable.rest));
        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));



    }

  /*  private void initdata(){
        place=new ArrayList<>();
        place.add(new Place_Model("Restaurant",R.drawable.rest));
        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));
        place.add(new Place_Model("Restaurant",R.drawable.rest));
        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));
        place.add(new Place_Model("Restaurant",R.drawable.rest));
        place.add(new Place_Model("Cafe",R.drawable.ger));
        place.add(new Place_Model("Pharmacy",R.drawable.download));


    }*/

}