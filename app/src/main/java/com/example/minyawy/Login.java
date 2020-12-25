package com.example.minyawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button logBtn;
    private ProgressBar progressBar_log;
    private TextView textViewRegister;
    private FirebaseAuth mAuth;
    
    FragmentManager manger=getSupportFragmentManager();
    FragmentTransaction t=manger.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        userMail=(EditText)findViewById(R.id.log_Email);
        userPassword=(EditText)findViewById(R.id.log_password);
        textViewRegister=(TextView)findViewById(R.id.textRegister);
        logBtn=(Button)findViewById(R.id.btnLogin);
        progressBar_log=(ProgressBar)findViewById(R.id.progressBar_log);
        progressBar_log.setVisibility(View.INVISIBLE);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent=new Intent(Login.this,Register_Activity.class);
                startActivity(registerIntent);
            }
        });

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar_log.setVisibility(View.VISIBLE);
                logBtn.setVisibility(View.INVISIBLE);

                 String log_mail = userMail.getText().toString();
                final String log_password = userPassword.getText().toString();


                if(log_mail.isEmpty() || log_password.isEmpty())
                {
                    showMessage("please verify all filed");
                }

                else
                {
                   /* Bundle bundle=new Bundle();
                    bundle.putString("email0",log_mail);
                    ProfileFragment pf=new ProfileFragment();
                    pf.setArguments(bundle);*/
 ////////////////////////////////////////////////////
                /*    Intent in = new Intent (Login.this, ProfileFragment.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name0", log_mail);
                    in.putExtras(bundle);
                    startActivity(in);*/
///////////////////////////////////////////////////////
                    // getMyData();
                    signIn(log_mail,log_password);
                }

            }
        });
    }

    private void signIn(String log_mail, String log_password) {

        mAuth.signInWithEmailAndPassword(log_mail,log_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    logBtn.setVisibility(View.VISIBLE);
                    progressBar_log.setVisibility(View.INVISIBLE);
                  //  isUser();

                    Intent intent=new Intent(Login.this, HomeActivity.class);
                   startActivity(intent);
                   finish();
                }

                else
                {
                    showMessage(task.getException().getMessage());
                    logBtn.setVisibility(View.VISIBLE);
                    progressBar_log.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }




    //get user information

    public void isUser (){
        final String UserEnteredUserName= userMail.getText().toString().trim();
        final String UserEnteredPassword=userPassword.getText().toString().trim();

        DatabaseReference Reference= FirebaseDatabase.getInstance().getReference("user");

        Query CheckUser=Reference.orderByChild("username").equalTo(UserEnteredUserName);
        CheckUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String passwordFromDB=snapshot.child(UserEnteredUserName).child("password").getValue(String.class);
                    if (passwordFromDB.equals(UserEnteredPassword)){
                        String nameFromDB=snapshot.child(UserEnteredUserName).child("name").getValue(String.class);
                        String emailFromDB=snapshot.child(UserEnteredUserName).child("username").getValue(String.class);
                     //   String photoFromDB=snapshot.child("photo").getValue(String.class);
                      /*  Intent profilInt=new Intent(Login.this,ProfileFragment.class);
                        profilInt.putExtra("name",nameFromDB);
                        profilInt.putExtra("email",emailFromDB);
                        profilInt.putExtra("photo",photoFromDB);
                        startActivity(profilInt);*/
                        Bundle bundle=new Bundle();
                        bundle.putString("name",nameFromDB);
                        bundle.putString("email",emailFromDB);
                      //  bundle.putString("photo",photoFromDB);
                        ProfileFragment pf=new ProfileFragment();
                        pf.setArguments(bundle);
                        t.add(R.id.Frag_container,pf);
                        t.commit();

                    }else
                        userPassword.setError("Wrong password");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public String getMyData() {

        String log_mail = userMail.getText().toString();
        return log_mail;
    }

}