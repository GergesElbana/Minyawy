package com.example.minyawy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Menues extends AppCompatActivity {
    ImageView photo_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menues);
        photo_menu=(ImageView)findViewById(R.id.pho_menue);
        String placPhoto=getIntent().getExtras().getString("photo");
        Glide.with(this).load(placPhoto).into(photo_menu);
    }
}