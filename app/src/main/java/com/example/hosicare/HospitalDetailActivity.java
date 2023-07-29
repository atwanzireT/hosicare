package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hosicare.adaptor.DoctorAdaptor;
import com.example.hosicare.modals.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HospitalDetailActivity extends AppCompatActivity {
    public static final String EXTRA_HOSP_ID = "hosp_id";
    RecyclerView docRV;
    ArrayList<Doctor> list;
    DoctorAdaptor doctorAdaptor;
    String hosp_id;
    Button addDocBtn, addBldGrpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_detail);


        docRV = findViewById(R.id.iddocRV);
        list = new ArrayList<>();
        docRV.setLayoutManager(new LinearLayoutManager(this));
        doctorAdaptor = new DoctorAdaptor(this, list);
        docRV.setAdapter(doctorAdaptor);
        hosp_id = getIntent().getStringExtra("hospID");
        System.out.println(hosp_id);
        addDocBtn = findViewById(R.id.idAddDocBtn);
        addBldGrpBtn = findViewById(R.id.idAddbloodBtn);

        addBldGrpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HospitalDetailActivity.this, BloodGrpActivity.class)
                        .putExtra(EXTRA_HOSP_ID, hosp_id));
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString().equals("admin@hosicare.com")) {
            addDocBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(HospitalDetailActivity.this, NewDoctorActivity.class)
                            .putExtra(EXTRA_HOSP_ID, hosp_id));
                }
            });
            Toast.makeText(this, hosp_id, Toast.LENGTH_SHORT).show();
        }else{
            addDocBtn.setVisibility(View.GONE);
//            Toast.makeText(HospitalDetailActivity.this, "Login as Admin", Toast.LENGTH_SHORT).show();
        }
        getDoctors();

    }

    private void getDoctors(){
        list.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("doctors");
        Query query = ref.orderByChild("hospitalid").equalTo(hosp_id);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Doctor doctor = dataSnapshot.getValue(Doctor.class);
                            list.add(doctor);
                        }
                        doctorAdaptor.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}