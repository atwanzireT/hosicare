package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hosicare.modals.MedicineModal;
import com.example.hosicare.modals.Patient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewMedicineActivity extends AppCompatActivity {
    TextView nametxt, agetxt, treatmenttxt;
    String name, age, treatment;
    Button medsave;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);

        nametxt = findViewById(R.id.medNameField);
        agetxt = findViewById(R.id.medAgeField);
        treatmenttxt = findViewById(R.id.medtreatmentField);
        medsave = findViewById(R.id.medSaveBtn);

        medsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nametxt.getText().toString();
                age = agetxt.getText().toString();
                treatment = treatmenttxt.getText().toString();
                mAuth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(treatment)) {
                    Toast.makeText(NewMedicineActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseReference medRef = FirebaseDatabase.getInstance().getReference("medicine");
                    String medId = medRef.push().getKey();
                    medRef.child(medId).setValue(new MedicineModal(name, age, treatment))
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        nametxt.setText("");
                                        agetxt.setText("");
                                        treatmenttxt.setText("");
                                        Toast.makeText(NewMedicineActivity.this, "New Doctor Added ...", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(NewMedicineActivity.this, "Unsuccessful ...", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                }
            });
    }
}