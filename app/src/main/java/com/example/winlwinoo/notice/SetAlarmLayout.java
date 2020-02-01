package com.example.winlwinoo.notice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class SetAlarmLayout extends AppCompatActivity {

    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    public Button alarmSet , alarmCancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_layout);

        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //alarmSet = (Button) findViewById(R.id.alarmSet);
        //alarmCancel = (Button) findViewById(R.id.alarmCancel);

    }

  /*  public void alarmSet(View view){

        long time;

        Toast.makeText(SetAlarmLayout.this, "Alarm On", Toast.LENGTH_SHORT).show();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
        if(System.currentTimeMillis()>time)
        {
            if (calendar.AM_PM == 0)
                time = time + (1000*60*60*12);
            else
                time = time + (1000*60*60*24);
        }
      alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000, pendingIntent);



    }

    public void alarmCancel(View view){
        alarmManager.cancel(pendingIntent);
        Toast.makeText(SetAlarmLayout.this, "Alarm Off", Toast.LENGTH_SHORT).show();

    }*/

    public void OnToggleClicked(View view)
    {

        if (((ToggleButton) view).isChecked())
        {
            Toast.makeText(SetAlarmLayout.this, "Alarm On", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
            Intent intent = new Intent(this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);


            /*alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,time,1000, pendingIntent);*/
            alarmManager.set(AlarmManager.RTC , calendar.getTimeInMillis(),pendingIntent);
        }
        else
        {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
            Toast.makeText(SetAlarmLayout.this, "Alarm Off", Toast.LENGTH_SHORT).show();
        }
    }
}
