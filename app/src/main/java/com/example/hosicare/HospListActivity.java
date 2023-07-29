package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hosicare.adaptor.HospitalAdaptor;
import com.example.hosicare.modals.HospitalModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HospListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HospitalAdaptor adapter;
    private ArrayList<HospitalModal> hospitalList;
    private HospitalAdaptor.OnHospitalListener onHospitalListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosp_list);

        recyclerView = findViewById(R.id.hospRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        onHospitalListener = new HospitalAdaptor.OnHospitalListener() {
            @Override
            public void onHospitalClicked(int position) {
//                Toast.makeText(HospListActivity.this, "Clicked" + hospitalList.get(position).getHosiID(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HospListActivity.this, HospitalDetailActivity.class)
                        .putExtra("hospID", hospitalList.get(position).getHosiID()));
            }
        };

        hospitalList = new ArrayList<>();
        adapter = new HospitalAdaptor(HospListActivity.this, hospitalList, onHospitalListener);
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