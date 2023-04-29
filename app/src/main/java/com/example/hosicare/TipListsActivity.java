package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.hosicare.adaptor.TipAdaptor;
import com.example.hosicare.adaptor.UsersAdaptor;
import com.example.hosicare.modals.Tips;
import com.example.hosicare.modals.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TipListsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Tips> tips;
    private ProgressBar progressBar;
    private TipAdaptor tipAdaptor;
    TipAdaptor.OnTipClickListener onTipClickListener;
    private SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        recyclerView = findViewById(R.id.tipRV);
        progressBar = findViewById(R.id.tipprogressbar);
        tips = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_lists);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(TipListsActivity.this, UserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void getTips(){
        tips.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    tips.add(dataSnapshot.getValue(Tips.class));
                }
                tipAdaptor = new TipAdaptor(tips, TipListsActivity.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(TipListsActivity.this));
                recyclerView.setAdapter(tipAdaptor);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}