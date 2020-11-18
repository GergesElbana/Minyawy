package com.example.minyawy;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceListActivity extends AppCompatActivity {


    @BindView(R.id.PlaceList)
    RecyclerView PlaceRecycler;

    private Context context;
    private List<ItemPlaceModel> placesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        ButterKnife.bind(this);

        placesList=new ArrayList<>();
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("ملاكي","نونه كشري احلا طعم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("ملاكي","نونه كشري احلا طعم",R.drawable.ger));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.download));
        placesList.add(new ItemPlaceModel("نهولة","نونه كشري احلا طعم",R.drawable.rest));
        placesList.add(new ItemPlaceModel("ملاكي","نونه كشري احلا طعم",R.drawable.ger));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        PlaceRecycler.setLayoutManager(linearLayoutManager);
        Places_Adapter adapter =new Places_Adapter(placesList);
        PlaceRecycler.setAdapter(adapter);


    }
}