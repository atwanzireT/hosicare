package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hosicare.modals.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class NewPatientActivity extends AppCompatActivity {
    EditText usernamefield, fnamefield, lnamefield, emailfield, usertypefield, sickoffield, passwordfield;
    String username, firstname, lastname, email, usertpye, sickof, password;
    Button savebtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        usernamefield = findViewById(R.id.patUsernameField);
        fnamefield = findViewById(R.id.patFirstNameField);
        lnamefield = findViewById((R.id.patLastNameField));
        emailfield = findViewById(R.id.patEmailField);
        usertypefield = findViewById(R.id.patTypeField);
        sickoffield = findViewById(R.id.sickofField);
        passwordfield = findViewById(R.id.patPwdField);
        savebtn = findViewById(R.id.savePatientBtn);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernamefield.getText().toString();
                firstname = fnamefield.getText().toString();
                lastname = lnamefield.getText().toString();
                email = emailfield.getText().toString();
                usertpye = usertypefield.getText().toString();
                sickof = sickoffield.getText().toString();
                password = passwordfield.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(email)
                 || TextUtils.isEmpty(usertpye) || TextUtils.isEmpty(sickof) || TextUtils.isEmpty(password)) {
                    Toast.makeText(NewPatientActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseDatabase.getInstance().getReference("patients"  + FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(new Patient(username, firstname, lastname, email, usertpye, sickof, password))
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(NewPatientActivity.this, "New Patient Added ...", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(NewPatientActivity.this, "Unsuccessful ...", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(NewPatientActivity.this, "Failed to Create Account ...", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}