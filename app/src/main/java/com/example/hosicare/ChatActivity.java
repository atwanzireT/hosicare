package com.example.hosicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.hosicare.adaptor.UsersAdaptor;
import com.example.hosicare.modals.User;

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
    }
}