package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class TipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_profile){
            startActivity(new Intent(TipsActivity.this, UserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void toCreateTip(View view) {
        startActivity(new Intent(this, CreateTipActivity.class));
    }

    public void toViewtips(View view) {
        startActivity(new Intent(this, TipListsActivity.class));
    }
}