package com.example.hosicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hosicare.modals.Tips;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTipActivity extends AppCompatActivity {
    EditText titlefield, detailfield;
    String title, details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tip);
    }

    public void saveTipFunc(View view) {
        titlefield = findViewById(R.id.titleField);
        detailfield = findViewById(R.id.detailField);

        title = titlefield.getText().toString();
        details = detailfield.getText().toString();

        if (TextUtils.isEmpty(title) || (TextUtils.isEmpty(details))){
            Toast.makeText(this, "All Fields must be filled .", Toast.LENGTH_SHORT).show();
        }else{
            FirebaseDatabase.getInstance().getReference("tips").setValue(new Tips(title, details))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(CreateTipActivity.this, "New Tip Created", Toast.LENGTH_SHORT).show();
                                titlefield.setText("");
                                detailfield.setText("");
                            }else{
                                Toast.makeText(CreateTipActivity.this, "Unsuccessful .", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }

//    public void saveDataFunc(View view) {
//    }
}