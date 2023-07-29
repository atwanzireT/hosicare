package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hosicare.adaptor.BloodAdaptor;
import com.example.hosicare.adaptor.MedicineAdaptor;
import com.example.hosicare.modals.BloodModal;
import com.example.hosicare.modals.MedicineModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MedicineListActivity extends AppCompatActivity {
    ArrayList<MedicineModal> list;
    RecyclerView recyclerView;
    MedicineAdaptor medicineAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        list = new ArrayList<>();
        recyclerView = findViewById(R.id.idMedList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicineAdaptor = new MedicineAdaptor(this, list);
        recyclerView.setAdapter(medicineAdaptor);
        getMedicine();
    }
    private void getMedicine(){
        list.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("medicine");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    MedicineModal medicineModal = dataSnapshot.getValue(MedicineModal.class);
                    list.add(medicineModal);
                }
                medicineAdaptor.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}