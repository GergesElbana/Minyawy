package com.example.minyawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");

        mAuth=FirebaseAuth.getInstance();

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

    }

    private void createUserAccount(final String str_name, final String str_email, final String str_password) {

        //this method create user account with specific email and password
        mAuth.createUserWithEmailAndPassword(str_email,str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            //user account created successful
                            showMessage("Account Created");
                            regesterBtn.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                            usermodel =new UserModel(str_email,str_password,str_name);
                            String  Email =email.getText().toString();
                            Log.v("gerges",Email);

                            databaseReference.push().setValue(usermodel)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register_Activity.this, "successful", Toast.LENGTH_SHORT).show();

                                }
                            });

                          //  Intent intent=new Intent(Register_Activity.this, HomeActivity.class);
                          //  startActivity(intent);
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

    private void showMessage(String message) {
        Toast.makeText(this,message , Toast.LENGTH_SHORT).show();
    }
}