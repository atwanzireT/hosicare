package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    LinearLayout adminlybtn, chatlybtn, alarmlybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adminlybtn = (LinearLayout) findViewById(R.id.adminbtn);
        chatlybtn = (LinearLayout) findViewById(R.id.chatlybtn);
        alarmlybtn = (LinearLayout) findViewById(R.id.alarmlyBtn);
        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString().equals("admin@hosicare.com")){
            adminlybtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent adminIntent = new Intent(HomeActivity.this, AdminPanelActivity.class);
                    startActivity(adminIntent);
                }
            });
        }else {
            Toast.makeText(HomeActivity.this, "Not Admin .", Toast.LENGTH_SHORT).show();
        }

        chatlybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(HomeActivity.this, ChatActivity.class);
                startActivity(chatIntent);
            }
        });
        alarmlybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AlarmActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(HomeActivity.this, UserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void switchToTip(View view) {
        startActivity(new Intent(this, TipsActivity.class));
    }

    public void viewSearchList(View view) {
        startActivity(new Intent(HomeActivity.this, SearchListActivity.class));
    }
}