package com.example.minyawy;

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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.minyawy.Admin.RestaurantDescriptionAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Register_Activity extends AppCompatActivity {

    private EditText name, email, password, confirmPassword;
    private Button regesterBtn;
   private ProgressBar progressBar;
   private   String str_name;
   private String str_email;
   private String str_password;
   private UserModel usermodel;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

   private FirebaseAuth mAuth;
   private ImageView ProfilPhoto;
   private int PReqCode=1;
   private int REQUESCODE=1;
    private Uri picturUri;
    public static String iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");

        mAuth=FirebaseAuth.getInstance();

        ProfilPhoto=(ImageView)findViewById(R.id.profilePhoto);
        name=(EditText)findViewById(R.id.reg_name);
        email=(EditText)findViewById(R.id.reg_email);
        password=(EditText)findViewById(R.id.reg_password);
        confirmPassword=(EditText)findViewById(R.id.reg_cnfirmPassword);
        regesterBtn=(Button)findViewById(R.id.btnRegister);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        regesterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                regesterBtn.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                 str_name = name.getText().toString();
                 str_email =email.getText().toString();
                 str_password =password.getText().toString();
                String str_confirmPassword =confirmPassword.getText().toString();

                if(str_name.isEmpty() || str_email.isEmpty() || str_password.isEmpty() || !str_confirmPassword.equals(str_password))
                {
                    //Toast.makeText(Register_Activity.this, "please verify all filed", Toast.LENGTH_SHORT).show();
                    showMessage("please verify all filed");
                    regesterBtn.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }

                else
                {
                    createUserAccount(str_name, str_email, str_password);
                }
                

            }
            
        });

        ProfilPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGallery();
            }
        });

    }

    private void checkGallery()
    {

        if (ContextCompat.checkSelfPermission(Register_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(Register_Activity.
                    this,Manifest.permission.ACTIVITY_RECOGNITION))
            {
                Toast.makeText(Register_Activity.this,"Please permission to access gallery",
                        Toast.LENGTH_SHORT).show();
            }

            else
            {
                ActivityCompat.requestPermissions(Register_Activity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqCode);
            }
        }
        else {
            openGallery();
        }
    }

    private void openGallery()
    {

        Intent galleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==REQUESCODE && resultCode ==RESULT_OK && data != null)
        {
            picturUri=data.getData();
            ProfilPhoto.setImageURI(picturUri);
        }
    }
    private void createUserAccount(final String str_name, final String str_email, final String str_password) {

        //this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(str_email,str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            updateUserInfo(str_name,picturUri,mAuth.getCurrentUser());

                            //user account created successful
                            showMessage("Account Created");
                            regesterBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            usermodel =new UserModel(str_email,str_password,str_name);
                            String  Email =email.getText().toString();
                            Log.v("gerges",Email);

                             iid=databaseReference.push().getKey();

                            databaseReference.child("info").child(iid).setValue(usermodel)


                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register_Activity.this, "successful", Toast.LENGTH_SHORT).show();

                                }
                            });

                            Intent intent=new Intent(Register_Activity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }




                        else
                        {
                            showMessage("account creation filed"+task.getException().getMessage());
                            regesterBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });
    }

    private void updateUserInfo(final String name, Uri picturUri, final FirebaseUser currentUser) {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference("Users_Photo");
        final StorageReference imagePath = storageReference.child(picturUri.getLastPathSegment());
        imagePath.putFile(picturUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdate= new UserProfileChangeRequest.Builder()
                                .setDisplayName(name).setPhotoUri(uri).build();

                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful())
                                        {
                                            showMessage("Register Complete");
                                        }
                                        else
                                        {
                                            showMessage("you do not change your profile");
                                        }

                                    }
                                });
                    }
                });

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this,message , Toast.LENGTH_SHORT).show();
    }
}