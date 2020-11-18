package com.example.minyawy;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    ArrayList<Place_Model> place;



    @BindView(R.id.PlaceRecycler)
    RecyclerView PlaceRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ImageSlider HomeSlider=(ImageSlider)findViewById(R.id.Slider);
/*
      //  slideModels.add(new SlideModel()
        slideModels.add(new SlideModel("https://unsplash.com/photos/S9OCBwRFV_k","img2"));
        slideModels.add(new SlideModel("https://unsplash.com/photos/S9OCBwRFV_k","img3"));


        HomeSlider.setImageList(slideModels,true);*/








        recyclerView = findViewById(R.id.PlaceRecycler);
        //initdata();
        ArrayList<Place_Model> place = new ArrayList<>();

        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        RecyclerAdapter adapter = new RecyclerAdapter(place, context);
        //  LinearLayoutManager LayoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);

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