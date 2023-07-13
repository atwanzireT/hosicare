package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hosicare.modals.HospitalModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewHospitalActivity extends AppCompatActivity {
    EditText namefield, postofficeField, locationfield, levelfield;
    String name, postoffice, location, level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_hospital);


    }

    public void saveDataFunc(View view) {
        namefield = findViewById(R.id.nameField);
        postofficeField = findViewById(R.id.postOfficeField);
        locationfield = findViewById(R.id.locationField);
        levelfield = findViewById(R.id.levelField);

        name = namefield.getText().toString();
        postoffice = postofficeField.getText().toString();
        location = locationfield.getText().toString();
        level = levelfield.getText().toString();

        if (TextUtils.isEmpty(name) || (TextUtils.isEmpty(postoffice)) || (TextUtils.isEmpty(location)) || (TextUtils.isEmpty(level))){
            Toast.makeText(this, "All Fields must be filled .", Toast.LENGTH_SHORT).show();
        }else{
            DatabaseReference hospRef = FirebaseDatabase.getInstance().getReference("hospital");
            String hospId = hospRef.push().getKey();
            hospRef.child(hospId).setValue(new HospitalModal(name, postoffice, location, level))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                namefield.setText("");
                                postofficeField.setText("");
                                locationfield.setText("");
                                levelfield.setText("");
                                Toast.makeText(NewHospitalActivity.this, "New Hospital Saved .", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(NewHospitalActivity.this, "Unsuccessful .", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }
}