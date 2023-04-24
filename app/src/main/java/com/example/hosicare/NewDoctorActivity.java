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

public class NewDoctorActivity extends AppCompatActivity {
    EditText usernamefield, fnamefield, lnamefield, emailfield, usertypefield, specilistfield, passwordfield;
    String username, firstname, lastname, email, usertype, specilist, password;
    Button savebtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor);

        usernamefield = findViewById(R.id.docUsernameField);
        fnamefield = findViewById(R.id.docFirstNameField);
        lnamefield = findViewById((R.id.docLastNameField));
        emailfield = findViewById(R.id.docEmailField);
        usertypefield = findViewById(R.id.docTypeField);
        specilistfield = findViewById(R.id.docSpecialismField);
        passwordfield = findViewById(R.id.docPwdField);
        savebtn = findViewById(R.id.docSaveBtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernamefield.getText().toString();
                firstname = fnamefield.getText().toString();
                lastname = lnamefield.getText().toString();
                email = emailfield.getText().toString();
                usertype = usertypefield.getText().toString();
                specilist = specilistfield.getText().toString();
                password = passwordfield.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(email)
                        || TextUtils.isEmpty(usertype) || TextUtils.isEmpty(specilist) || TextUtils.isEmpty(password)) {
                    Toast.makeText(NewDoctorActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                FirebaseDatabase.getInstance().getReference("doctors"  + FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(new Patient(username, firstname, lastname, email, usertype, specilist, password))
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    usernamefield.setText("");
                                                    fnamefield.setText("");
                                                    lnamefield.setText("");
                                                    emailfield.setText("");
                                                    specilistfield.setText("");
                                                    passwordfield.setText("");
                                                    Toast.makeText(NewDoctorActivity.this, "New Doctor Added ...", Toast.LENGTH_SHORT).show();
                                                }
                                                else{
                                                    Toast.makeText(NewDoctorActivity.this, "Unsuccessful ...", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            }else{
                                Toast.makeText(NewDoctorActivity.this, "Failed to Create Account ...", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}