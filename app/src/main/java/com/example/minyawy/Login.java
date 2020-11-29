package com.example.minyawy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Login extends AppCompatActivity {

    private EditText userMail, userPassword;
    private Button logBtn;
    private ProgressBar progressBar_log;
    private TextView textViewRegister;
    private FirebaseAuth mAuth;
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

                final String log_mail = userMail.getText().toString();
                final String log_password = userPassword.getText().toString();

                if(log_mail.isEmpty() || log_password.isEmpty())
                {
                    showMessage("please verify all filed");
                }

                else
                {
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
                    Intent intent=new Intent(Login.this, HomeActivity.class);
                    startActivity(intent);
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
}