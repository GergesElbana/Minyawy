package com.example.minyawy.Admin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.telephony.ims.RcsUceAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minyawy.FetchPlaceName;
import com.example.minyawy.HomeActivity;
import com.example.minyawy.PlaceListActivity;
import com.example.minyawy.PlaceListActivity_ViewBinding;
import com.example.minyawy.Places_Adapter;
import com.example.minyawy.R;
import com.example.minyawy.RecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;
import java.util.jar.Attributes;

public class RestaurantDescriptionAdmin extends AppCompatActivity {

    private ImageView placeLogo, menuPhoto_1, menuPhoto_2;
    private EditText placeName, placeLocation, placeDescrip, placePhone;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference, dataPhoto;
    private Uri picturUri, mPhoto_1,mPhoto_2;
    private final int PReqCode = 1;
    private static final int REQUESCODE = 1;
    private Button send;
    public static String id0;
    //خدت اوبجت من الادابتر علشان اعرف ااكسس التكست اللي عايزه
    private RecyclerAdapter b;
    private String PlaceNameList[] = {"Cafe", "Restaurant", "clothes", "bank", "hotel", "hospital", "Pharmacy", "Men_Suit"};
    private String id = UUID.randomUUID().toString();
   // private String imadeDownload;
    private String photoNum;
    private  String string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_description_admin);

        // firebaseDatabase=FirebaseDatabase.getInstance();
        //  databaseReference=firebaseDatabase.getReference("Restaurant");
        // id0=databaseReference.push().getKey();


        //init view
        placeLogo = (ImageView) findViewById(R.id.Admin_PlaceLogo);
        menuPhoto_1 = (ImageView) findViewById(R.id.image_menu1);
        menuPhoto_2 = (ImageView) findViewById(R.id.image_menu2);
        send = (Button) findViewById(R.id.send);
        placeName = (EditText) findViewById(R.id.Admin_PlaceName);
        placeLocation = (EditText) findViewById(R.id.Admin_PlaceLocation);
        placeDescrip = (EditText) findViewById(R.id.Admin_PlaceDescription);
        placePhone = (EditText) findViewById(R.id.Admin_PlaceNumber);

        setupPost();

        placeLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                photoNum="0";
                //  checkGallery();
                openGallery();

            }
        });

       menuPhoto_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // checkGallery();
                photoNum="1";
                openGallery();
            }
        });

        menuPhoto_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoNum="2";
                openGallery();
            }
        });
    }


    private void checkGallery() {

        if (ContextCompat.checkSelfPermission(RestaurantDescriptionAdmin.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(RestaurantDescriptionAdmin.this, Manifest.permission.ACTIVITY_RECOGNITION)) {
                Toast.makeText(RestaurantDescriptionAdmin.this, "Please permission to access gallery", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(RestaurantDescriptionAdmin.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }
        } else {
            openGallery();
        }


    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);
    }

    private void setupPost() {

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sentDataStorage(picturUri);
                getphoyo_1(mPhoto_1);
                getphoyo_2(mPhoto_2);

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void addPosttoDatabase(Admin_SendData fetchPlaceName) {

        //String key =databaseReference.getKey();
        //نونة بتفتي فتي يعمل مصايب
        //  fetchPlaceName.setId(id);
        //الفنكشن دي شكلها ممكن تغير الداتا في الادبتر وتبقي حاجه عنب ههه
        // b.setdata();

        for (int i = 0; i < PlaceNameList.length; i++) {


            if (b.placenametext.equals(PlaceNameList[i])) {
                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference(b.placenametext);
                id0 = databaseReference.push().getKey();

                databaseReference.child(id).setValue(fetchPlaceName)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                showMessage("successful");
                            }
                        });
           /*  Intent nopnty=new Intent(RestaurantDescriptionAdmin.this, PlaceListActivity_ViewBinding.class);
                            startActivity(nopnty);*/
            }
        }
       /* databaseReference.push().setValue(fetchPlaceName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("successful");

                    }
                });*/
    }

    public void addPhoto_1( Admin_SendData admin_sendData)
    {

       string=placeName.getText().toString();
                firebaseDatabase = FirebaseDatabase.getInstance();
                dataPhoto = firebaseDatabase.getReference("menu_photo");
                //id0 = databaseReference.push().getKey();

                         dataPhoto.child(string).setValue(admin_sendData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                showMessage("successful photoooooo");
                            }
                        });

    }

    public void addPhoto_2( Admin_SendData admin_sendData)
    {

        string=placeName.getText().toString();
        firebaseDatabase = FirebaseDatabase.getInstance();
        dataPhoto = firebaseDatabase.getReference("menu_photo_2");
        //id0 = databaseReference.push().getKey();

        dataPhoto.child(string).setValue(admin_sendData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("successful photoooooo2222");
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUESCODE && resultCode == RESULT_OK) {
            if(photoNum=="0") {
                picturUri = data.getData();
                placeLogo.setImageURI(picturUri);
            }
            else if (photoNum=="1")
            {
                mPhoto_1=data.getData();
                menuPhoto_1.setImageURI(mPhoto_1);
            }
            else if(photoNum=="2")
            {
                mPhoto_2=data.getData();
                menuPhoto_2.setImageURI(mPhoto_2);
            }

        }
    }

    public void sentDataStorage(Uri uri){
        final String nono = placeName.getText().toString();
        if (!nono.isEmpty() || !placeDescrip.getText().toString().isEmpty() ||
                !placeLocation.getText().toString().isEmpty() || !placePhone.getText().toString().isEmpty() && uri != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("photo");
            final StorageReference imagePath = storageReference.child(uri.getLastPathSegment());
            imagePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imadeDownload = uri.toString();

                            Admin_SendData fetchPlaceName = new Admin_SendData(imadeDownload, placeName.getText().toString()
                                    , placeDescrip.getText().toString(), placeLocation.getText().toString()
                                    , placePhone.getText().toString(), id0);
                            addPosttoDatabase(fetchPlaceName);
                            Intent nonty = new Intent(RestaurantDescriptionAdmin.this, PlaceListActivity.class);
                            startActivity(nonty);

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

        else {
            showMessage("Please full Data");
        }

    }
    public void getphoyo_1(Uri uri_1)
    {
        final String nono = placeName.getText().toString();
        if (!nono.isEmpty() || !placeDescrip.getText().toString().isEmpty() ||
                !placeLocation.getText().toString().isEmpty() || !placePhone.getText().toString().isEmpty() && uri_1 != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("photo");
            final StorageReference imagePath = storageReference.child(uri_1.getLastPathSegment());
            imagePath.putFile(uri_1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imadeDownload = uri.toString();

                            Admin_SendData fetchPlaceName = new Admin_SendData(imadeDownload);
                            addPhoto_1(fetchPlaceName);

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

    public void getphoyo_2(Uri uri_2)
    {
        final String nono = placeName.getText().toString();
        if (!nono.isEmpty() || !placeDescrip.getText().toString().isEmpty() ||
                !placeLocation.getText().toString().isEmpty() || !placePhone.getText().toString().isEmpty() && uri_2 != null) {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference("photo");
            final StorageReference imagePath = storageReference.child(uri_2.getLastPathSegment());
            imagePath.putFile(uri_2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String imadeDownload = uri.toString();

                            Admin_SendData fetchPlaceName = new Admin_SendData(imadeDownload);
                            addPhoto_2(fetchPlaceName);

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

}