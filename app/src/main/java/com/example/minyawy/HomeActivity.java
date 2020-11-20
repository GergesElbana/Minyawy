package com.example.minyawy;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    ArrayList<Place_Model> place;


    @BindView(R.id.PlaceRecycler)
    RecyclerView PlaceRecycler;
    @BindView(R.id.Slider)
    ViewFlipper Slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);



        int imageslider[]={R.drawable.download,R.drawable.rest};
        for(int imegs:imageslider){
            ImageFlipper(imegs);

        }

        recyclerView = findViewById(R.id.PlaceRecycler);
        ArrayList<Place_Model> place = new ArrayList<>();
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        place.add(new Place_Model("Restaurant", R.drawable.rest));
        place.add(new Place_Model("Cafe", R.drawable.ger));
        place.add(new Place_Model("Pharmacy", R.drawable.download));
        RecyclerAdapter adapter = new RecyclerAdapter(place, context);

        //  LinearLayoutManager LayoutManager=new LinearLayoutManager(context);
       RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,
               StaggeredGridLayoutManager.VERTICAL);
              recyclerView.setLayoutManager(layoutManager);
              recyclerView.setItemAnimator(new DefaultItemAnimator());


       // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);


    }
     public void ImageFlipper(int image){
         ImageView imageView=new ImageView(this);
         imageView.setBackgroundResource(image);
         Slider.addView(imageView);
         Slider.setFlipInterval(4000);
         Slider.setAutoStart(true);
         Slider.setInAnimation(this,android.R.anim.slide_in_left);
         Slider.setOutAnimation(this,android.R.anim.slide_out_right);
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