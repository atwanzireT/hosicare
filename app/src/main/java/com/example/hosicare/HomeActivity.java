package com.example.hosicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    LinearLayout adminlybtn, chatlybtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adminlybtn = (LinearLayout) findViewById(R.id.adminbtn);
        chatlybtn = (LinearLayout) findViewById(R.id.chatlybtn);
        adminlybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(HomeActivity.this, AdminPanelActivity.class);
                startActivity(adminIntent);
            }
        });
        chatlybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(HomeActivity.this, ChatActivity.class);
            }
        });

    }
}