package com.example.minyawy;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDescription extends AppCompatActivity {

    @BindView(R.id.ReataurateImage)
    ImageView ReataurateImage;
    @BindView(R.id.RestaurantName)
    Toolbar RestaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description);
        ButterKnife.bind(this);

        RestaurantName.setTitle("Koshary Nahla");
        setSupportActionBar(RestaurantName);


    }
}