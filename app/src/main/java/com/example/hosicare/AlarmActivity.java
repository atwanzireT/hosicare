package com.example.hosicare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hosicare.AlarmReceiver;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {
    private TimePicker alarmTimePicker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmTimePicker = findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    }

    // OnToggleClicked() method is implemented for the toggle button to control the alarm functionality
    public void OnToggleClicked(View view) {
        long time;
        if (((ToggleButton) view).isChecked()) {
            Toast.makeText(this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();

            // Use the older approach to get the selected hour and minute
            int selectedHour = alarmTimePicker.getCurrentHour();
            int selectedMinute = alarmTimePicker.getCurrentMinute();

            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);

            // Check if the selected time is in the past
            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                // If it's in the past, add one day to the calendar
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            // Set the alarm using setExact() to trigger the alarm at the exact specified time
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}
