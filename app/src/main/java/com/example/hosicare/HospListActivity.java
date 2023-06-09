package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.hosicare.adaptor.HosiptalAdaptor;
import com.example.hosicare.modals.HospitalModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HospListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HosiptalAdaptor adapter;
    private ArrayList<HospitalModal> hospitalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_list);

        recyclerView = findViewById(R.id.hospRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        hospitalList = new ArrayList<>();
        adapter = new HosiptalAdaptor(HospListActivity.this, hospitalList);
        recyclerView.setAdapter(adapter);

        // Retrieve data from Firebase
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("hospital");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    HospitalModal hospitalModal = dataSnapshot.getValue(HospitalModal.class);
                    hospitalList.add(hospitalModal);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}