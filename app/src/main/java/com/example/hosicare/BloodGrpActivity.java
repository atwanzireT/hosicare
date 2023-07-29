package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hosicare.adaptor.BloodAdaptor;
import com.example.hosicare.adaptor.DoctorAdaptor;
import com.example.hosicare.modals.BloodModal;
import com.example.hosicare.modals.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BloodGrpActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    BloodAdaptor bloodAdaptor;
    ArrayList<BloodModal>  list;
    EditText bloodTxt;
    Button saveBlood;
    String bloodTypeTxt;
    String hosp_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_grp);
        recyclerView = findViewById(R.id.bloodRV);
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bloodAdaptor = new BloodAdaptor(this, list);
        recyclerView.setAdapter(bloodAdaptor);
        bloodTxt = findViewById(R.id.newbloodgrp);
        saveBlood = findViewById(R.id.saveBloodBtn);
        hosp_id =  getIntent().getStringExtra(HospitalDetailActivity.EXTRA_HOSP_ID);

        getBloodGrp();
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString().equals("admin@hosicare.com")) {
            saveBlood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bloodTypeTxt = bloodTxt.getText().toString();
                    if (TextUtils.isEmpty(bloodTypeTxt)) {
                        Toast.makeText(BloodGrpActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                    } else {
                        DatabaseReference bloodRef = FirebaseDatabase.getInstance().getReference("blood");
                        String bloodId = bloodRef.push().getKey();
                        bloodRef.child(bloodId).setValue(new BloodModal(bloodTypeTxt, hosp_id))
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            bloodTxt.setText("");
                                            Toast.makeText(BloodGrpActivity.this, "New blood Added ...", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(BloodGrpActivity.this, "Unsuccessful ...", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                    }
                }
            });
        }else{
            saveBlood.setVisibility(View.GONE);
            bloodTxt.setVisibility(View.GONE);
        }

    }

    private void getBloodGrp(){
        list.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("blood");
        Query query = ref.orderByChild("hosId").equalTo(hosp_id);
        query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            BloodModal bloodModal = dataSnapshot.getValue(BloodModal.class);
                            list.add(bloodModal);
                        }
                        bloodAdaptor.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}