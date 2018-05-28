package com.example.kiran.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DialogFragment.Communicator {
    TimePicker timePicker = null;
    Button pickButton;
    Button setButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickButton = findViewById(R.id.pick);
        setButton = findViewById(R.id.set);

        pickButton.setOnClickListener((v) -> {
            DialogFragment dialogFragment = new DialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "myDialog");
        });
        setButton.setOnClickListener((v) -> {
            if (timePicker != null) {
                Calendar calendar = Calendar.getInstance();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getHour(), timePicker.getMinute(),0);
                else
                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(), timePicker.getCurrentMinute(),0);

                setAlarm(calendar.getTimeInMillis());
            } else {
                Toast.makeText(this, "Pick the time first !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent);
        Toast.makeText(this, "Alarm is set !", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void communicate(TimePicker timePicker) {
        this.timePicker = timePicker;
    }
}
