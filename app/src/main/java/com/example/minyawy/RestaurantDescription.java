package com.example.minyawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDescription extends AppCompatActivity {


    @BindView(R.id.ReataurateImage)
    ImageView ReataurateImage;
    @BindView(R.id.RestaurantName)
    Toolbar RestaurantName;
    @BindView(R.id.RestaurantCaption)
    TextView RestaurantCaption;
    @BindView(R.id.tex_number)
    TextView texNumber;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.call_action)
    TextView callAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description);
        ButterKnife.bind(this);

        RestaurantName.setTitle("Koshary gerges");
        setSupportActionBar(RestaurantName);
        callAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numb = texNumber.getText().toString();
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:"+ numb));
                startActivity(call);
            }
        });


    }
}