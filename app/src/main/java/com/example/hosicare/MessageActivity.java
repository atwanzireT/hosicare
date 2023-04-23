package com.example.hosicare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hosicare.adaptor.MessageAdaptor;
import com.example.hosicare.modals.Message;
import com.example.hosicare.modals.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText editTextInput;
    private TextView txtChattingWith;
    private ImageView toolImgView, imgSend;
    private ProgressBar progressBar;


    private String username_of_roommate, email_of_roommate, chatRoomId;
    private MessageAdaptor messageAdaptor;

    private ArrayList<Message> messages = new ArrayList<>(); // initialize the ArrayList

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.recyclerMsg);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(messageAdaptor);

        editTextInput = findViewById(R.id.editMsgInput);
        txtChattingWith = findViewById(R.id.txtChattingWith);
        progressBar = findViewById(R.id.msgProgressBar);
        toolImgView = findViewById(R.id.img_toolbar);
        imgSend = findViewById(R.id.sendMsgBtn);

        username_of_roommate = getIntent().getStringExtra("username_of_roommate");
        email_of_roommate = getIntent().getStringExtra("email_of_roommate");
        txtChattingWith.setText(username_of_roommate);
        messageAdaptor = new MessageAdaptor(messages, getIntent().getStringExtra("my_img"), getIntent().getStringExtra("image_of_roommate"), MessageActivity.this);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("chats/" + chatRoomId + "/messages")
                        .push()
                        .setValue(new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(), email_of_roommate, editTextInput.getText().toString()));
                editTextInput.setText("");
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdaptor);

        Glide.with(MessageActivity.this).load(getIntent()
                        .getStringExtra("image_of_roommate"))
                .placeholder(R.drawable.account_img)
                .error(R.drawable.account_img);
        setUpChatRoom();
    }

    private void setUpChatRoom() {
        FirebaseDatabase.getInstance().getReference("user/" + FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        String myUsername = snapshot.getValue(User.class).getUsername();
                        if (username_of_roommate.compareTo(myUsername) > 0) {
                            chatRoomId = myUsername + "-" + username_of_roommate;
                        } else if (username_of_roommate.compareTo(myUsername) == 0) {
                            chatRoomId = myUsername + "-" + username_of_roommate;
                        } else {
                            chatRoomId = username_of_roommate + "-" + myUsername;
                        }
                        Log.d(TAG, "setUpChatRoom: chatRoomId: " + chatRoomId);
                        Log.d(TAG, "setUpChatRoom: reference: " + FirebaseDatabase.getInstance().getReference("chats/" + chatRoomId + "/messages").toString());
                        attachMessageListener(chatRoomId);

                    }



                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
    }
    private void attachMessageListener(String chatRoomId) {
        FirebaseDatabase.getInstance().getReference("chats/" + chatRoomId + "/messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear(); // clear the ArrayList before adding new messages
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    Log.d(TAG, "onDataChange: message: " + message.getContext());
                    messages.add(message);
                }

                messageAdaptor.notifyDataSetChanged(); // notify the adaptor of the data set change
                recyclerView.scrollToPosition(messages.size() - 1);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Failed to read value.", error.toException());
                Toast.makeText(MessageActivity.this, "Failed to read value.", Toast.LENGTH_SHORT).show();
            }
        });
    }



}