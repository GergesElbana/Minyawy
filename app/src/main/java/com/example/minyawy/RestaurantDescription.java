package com.example.minyawy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.minyawy.Admin.Admin_SendData;
import com.example.minyawy.Admin.RestaurantDescriptionAdmin;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
    private ArrayList<Admin_SendData> admin_sendDataArrayList;
    private ImageView menPhoto1,menPhoto2;
    private DatabaseReference  photoData, photoDataRef_2;
    FetchPlaceName mm;
    String placnum;
    String plac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description);
        ButterKnife.bind(this);

        menPhoto1=(ImageView)findViewById(R.id.m_image_1);
        menPhoto2=(ImageView)findViewById(R.id.m_image_2);



        String placPhoto=getIntent().getExtras().getString("photo");
        Glide.with(this).load(placPhoto).into(ReataurateImage);
        String plName=getIntent().getExtras().getString("name");
        RestaurantName.setTitle(plName);
        String plDiscr=getIntent().getExtras().getString("dis");
        RestaurantCaption.setText(plDiscr);
        String plNumber=getIntent().getExtras().getString("num");
        Resturantnumber.setText(plNumber);
        String plLocation=getIntent().getExtras().getString("loc");
        RestaurantLocation.setText(plLocation);
     /*   String menPhoto_1=getIntent().getExtras().getString("pho_1");
        Glide.with(this).load(menPhoto_1).into(menPhoto1);*/

        photoData=FirebaseDatabase.getInstance().getReference("menu_photo");
        photoDataRef_2=FirebaseDatabase.getInstance().getReference("menu_photo_2");

        //to get menu photo_1
        photoData.child(plNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // for(DataSnapshot data:snapshot.getChildren()){
                    //FetchPlaceName fetchPlaceName=new FetchPlaceName();
                    // fetchPlaceName.setPhoto_1(data.child("photo_1").getValue().toString());
                   FetchPlaceName fetchPlaceName=snapshot.getValue(FetchPlaceName.class);
                    Glide.with(RestaurantDescription.this).load(fetchPlaceName.getPhoto_1()).into(menPhoto1);
               // }
                Log.v("key",fetchPlaceName.getPhoto_1());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //to get menu photo_2
        photoDataRef_2.child(plNumber).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // for(DataSnapshot data:snapshot.getChildren()){
               //  fetchPlaceName=new FetchPlaceName();
                mm=snapshot.getValue(FetchPlaceName.class);
                Glide.with(RestaurantDescription.this).load(mm.getPhoto_1()).into(menPhoto2);
               // Log.v("key",mm.getPhoto_1());
                // }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RestaurantDescription.this, "can not load photo", Toast.LENGTH_SHORT).show();
            }
        });
        menPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RestaurantDescription.this,Menues.class);

                intent.putExtra("photo",mm.getPhoto());
                startActivity(intent);
            }
        });




       /* databaseReference= FirebaseDatabase.getInstance().getReference("Restaurant").child("non");
       /* Query query=databaseReference.child("Restaurant");
        query*databaseReference.child(Places_Adapter.Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

               // for(DataSnapshot data:snapshot.getChildren()) {

                    admin =snapshot.getValue(Admin_SendData.class);


                    RestaurantCaption.setText(admin.getDescrip());
                    RestaurantLocation.setText(admin.getLocation());
                    Glide.with(RestaurantDescription.this)
                            .load(admin.getPhoto()).into(ReataurateImage);
                    placnum = admin.getNumber();
                    plac = admin.getName();
                    Resturantnumber.setText(placnum);


                }




           // }

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
        });*/


    }


}