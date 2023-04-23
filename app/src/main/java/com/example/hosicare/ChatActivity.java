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
import android.widget.Toast;

import com.example.hosicare.adaptor.UsersAdaptor;
import com.example.hosicare.modals.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdaptor usersAdaptor;
    UsersAdaptor.OnUserClickListener onUserClickListener;
    private SwipeRefreshLayout refreshLayout;

    private String myImgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.user_rv);
        progressBar = findViewById(R.id.frnds_progressbar);
        users = new ArrayList<>();
        refreshLayout = findViewById(R.id.fndSwipeLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getUsers();
                refreshLayout.setRefreshing(false);
            }
        });

        onUserClickListener = new UsersAdaptor.OnUserClickListener() {
            @Override
            public void onUserClicked(int position) {
                startActivity(new Intent(ChatActivity.this, MessageActivity.class)
                        .putExtra("username_of_roommate", users.get(position).getUsername())
                        .putExtra("email_of_roommate", users.get(position).getEmail())
                        .putExtra("image_of_roommate", users.get(position).getProfilePicture())
                        .putExtra("my_img", myImgUrl));
//                Toast.makeText(FriendsActivity.this, "clicked on user" + users.get(position).getUsername(), Toast.LENGTH_SHORT).show();
            }
        };

        getUsers();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(ChatActivity.this, UserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUsers(){
        users.clear();
        FirebaseDatabase.getInstance().getReference("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    users.add(dataSnapshot.getValue(User.class));
                }
                usersAdaptor = new UsersAdaptor(users, ChatActivity.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                recyclerView.setAdapter(usersAdaptor);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

                for(User user : users){
                    if(user.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
                        myImgUrl = user.getProfilePicture();
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}