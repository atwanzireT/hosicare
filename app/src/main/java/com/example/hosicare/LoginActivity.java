package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = (Button) findViewById(R.id.loginBtn);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Toast.makeText(getApplicationContext(), "Already Signed in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
//        loginbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
//                startActivity(homeIntent);
//            }
//        });
    }


    public void handleSignin(View view) {
        EditText emailField, passwordField;
        ProgressBar bar;
        FirebaseAuth mAuth;
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.pwdField);
        mAuth = FirebaseAuth.getInstance();


        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "All Fields Must be Filled .", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, ChatActivity.class));
                        FirebaseDatabase.getInstance().getReference("user");
                        Intent loginIntent = new Intent(LoginActivity.this, HomeActivity.class);
                        loginIntent.putExtra("email", mAuth.getCurrentUser().getEmail());
                        loginIntent.putExtra("uid", mAuth.getCurrentUser().getUid());
                        startActivity(loginIntent);
                    } else {
                        emailField.setText("");
                        passwordField.setText("");
                        Toast.makeText(getApplicationContext(), "Process Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void noAccFunc(View view) {
        Intent noAccIntent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(noAccIntent);
    }
}