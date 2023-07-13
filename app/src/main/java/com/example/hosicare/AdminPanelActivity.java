package com.example.hosicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AdminPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

    }

    public void switchToHosp(View view) {
        startActivity(new Intent(this, NewHospitalActivity.class));
    }

    public void switchToDoc(View view) {
        startActivity(new Intent(this, NewDoctorActivity.class));

    }

    public void switchToPat(View view) {
        startActivity(new Intent(this, NewPatientActivity.class));
    }

    public void switchToMed(View view) {
        startActivity(new Intent(AdminPanelActivity.this, NewMedicineActivity.class));
    }

    public void switchToMedicine(View view) {
        startActivity(new Intent(AdminPanelActivity.this, NewMedicineActivity.class));
    }
}