package com.example.minyawy.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minyawy.FetchPlaceName;
import com.example.minyawy.PlaceListActivity_ViewBinding;
import com.example.minyawy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class RestaurantDescriptionAdmin extends AppCompatActivity {

    private ImageView placeLogo, menuPhoto;
    private EditText placeName, placeLocation, placeDescrip, placePhone;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Uri picturUri;
    private final int PReqCode=1;
    private final int REQUESCODE=1;
    private Button send;

    static String id= UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description_admin);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Restaurant");

        placeLogo=(ImageView)findViewById(R.id.Admin_PlaceLogo);
        send=(Button)findViewById(R.id.send);
        placeName=(EditText)findViewById(R.id.Admin_PlaceName);
        placeLocation=(EditText)findViewById(R.id.Admin_PlaceLocation);
        placeDescrip=(EditText)findViewById(R.id.Admin_PlaceDescription);
        placePhone=(EditText)findViewById(R.id.Admin_PlaceNumber);

        setupPost();

        placeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkGallery();

            }
        });
    }


    private void checkGallery()
    {

        if (ContextCompat.checkSelfPermission(RestaurantDescriptionAdmin.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(RestaurantDescriptionAdmin.this,Manifest.permission.ACTIVITY_RECOGNITION))
            {
                Toast.makeText(RestaurantDescriptionAdmin.this,"Please permission to access gallery",Toast.LENGTH_SHORT).show();
            }

            else
            {
                ActivityCompat.requestPermissions(RestaurantDescriptionAdmin.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }
        }

        else
        {
            openGallery();
        }


    }

    private void openGallery()
    {

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }

    private void setupPost() {

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

     String nono=placeName.getText().toString();

        if (!nono.isEmpty() || !placeDescrip.getText().toString().isEmpty()||
                !placeLocation.getText().toString().isEmpty()||!placePhone.getText().toString().isEmpty()&& picturUri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("photo");
            final StorageReference imagePath = storageReference.child(picturUri.getLastPathSegment());
            imagePath.putFile(picturUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imadeDownload = uri.toString();

                          Admin_SendData fetchPlaceName = new Admin_SendData(imadeDownload, placeName.getText().toString()
                                    ,placeDescrip.getText().toString(),placeLocation.getText().toString()
                          ,placePhone.getText().toString());
                            addPosttoDatabase(fetchPlaceName);
                           /* Intent nonty=new Intent(RestaurantDescriptionAdmin.this, PlaceListActivity_ViewBinding.class);
                            startActivity(nonty);*/

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showMessage(e.getMessage());
                        }
                    });

                }
            });
        }

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addPosttoDatabase(Admin_SendData fetchPlaceName)
    {

         //String key =databaseReference.getKey();
        fetchPlaceName.setId(id);

        databaseReference.child(id).setValue(fetchPlaceName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("successful");

                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==REQUESCODE && resultCode ==RESULT_OK && data != null)
        {
            picturUri=data.getData();
            placeLogo.setImageURI(picturUri);
        }
    }


}