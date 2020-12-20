package com.example.minyawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.minyawy.Admin.Admin_SendData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantDescription extends AppCompatActivity {


    @BindView(R.id.ReataurateImage)
    ImageView ReataurateImage;
    @BindView(R.id.RestaurantName)
    Toolbar RestaurantName;
    @BindView(R.id.RestaurantCaption)
    TextView RestaurantCaption;
    @BindView(R.id.RestaurantNumber)
    TextView Resturantnumber;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.call_action)
    TextView callAction;
    @BindView(R.id.RestaurantLocation)
    TextView RestaurantLocation;
    private DatabaseReference databaseReference;
    private    Admin_SendData admin;

    String placnum;
    String plac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description);
        ButterKnife.bind(this);
        databaseReference= FirebaseDatabase.getInstance().getReference("Restaurant");
       /* Query query=databaseReference.child("Restaurant");
        query*/databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot data:snapshot.getChildren()) {
          admin=data.getValue(Admin_SendData.class);

                    RestaurantCaption.setText(admin.getDescrip());
                    RestaurantLocation.setText(admin.getLocation());
                    Glide.with(RestaurantDescription.this)
                            .load(admin.getPhoto()).into(ReataurateImage);
                    placnum=admin.getNumber();
                    plac=admin.getName();
                    Resturantnumber.setText(placnum);

                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       RestaurantName.setTitle(plac);
        setSupportActionBar(RestaurantName);

        callAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numb = Resturantnumber.getText().toString();
                Intent call = new Intent(Intent.ACTION_CALL);
                call.setData(Uri.parse("tel:" + numb));
                startActivity(call);
            }
        });


    }


}