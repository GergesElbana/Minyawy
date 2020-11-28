package com.example.minyawy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;
    ArrayList<Place_Model> place;

    Toolbar toolbar;

    @BindView(R.id.navibotton)
    BottomNavigationView navibotton;
    //  @BindView(R.id.Slider)
    // ViewFlipper Slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

     /*   toolbar = findViewById(R.id.tool_Bar);
        setSupportActionBar(toolbar);

        ImageSlider imageSlider = findViewById(R.id.Slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.rest, null));
        slideModels.add(new SlideModel(R.drawable.rest, null));
        imageSlider.setImageList(slideModels, true);

       /* int imageslider[]={R.drawable.download,R.drawable.rest};
        for(int imegs:imageslider){
            ImageFlipper(imegs);

        }*/

       /* recyclerView = findViewById(R.id.PlaceRecycler);
        ArrayList<Place_Model> place = new ArrayList<>();
        place.add(new Place_Model("Cafe", R.drawable.cafe));
        place.add(new Place_Model("Pharmacy", R.drawable.pharmacy));
        place.add(new Place_Model("Restaurant", R.drawable.restaurant));
        place.add(new Place_Model("bank", R.drawable.bank));
        place.add(new Place_Model("clothes", R.drawable.clothes));
        place.add(new Place_Model("hotel", R.drawable.hotel));
        place.add(new Place_Model("hospital", R.drawable.hospital));
        place.add(new Place_Model("Men_Suit", R.drawable.men_suit));
        place.add(new Place_Model("souvenirs", R.drawable.sovener));
        place.add(new Place_Model("bread", R.drawable.bread));

        RecyclerAdapter adapter = new RecyclerAdapter(place, this);

        //  LinearLayoutManager LayoutManager=new LinearLayoutManager(context);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        // recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);*/
        getSupportFragmentManager().beginTransaction().replace(R.id.Frag_container, new HomeFragment()).commit();

        navibotton.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment=null;
                switch (item.getItemId()){
                 case   R.id.home:
                     selectedFragment =new HomeFragment();
                     break;
                    case   R.id.notification:
                        selectedFragment=new NotificationFragment();
                     break;
                    case   R.id.profile:
                        selectedFragment=new ProfileFragment();
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.Frag_container,selectedFragment).commit();
                return true;
            }
        });

    }



}