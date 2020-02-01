package com.example.winlwinoo.notice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by Win Lwin Oo on 5/27/2017.
 */

public class AlarmReceiver extends BroadcastReceiver
{
    Ringtone ringtone;

    @Override
    public void onReceive(Context context, Intent intent)
    {

        createNotification(context , "New Notice" , "Notice..." , "Notice Alert!");


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtone.stop();
            }
        } , 10000 );
    }

    private void createNotification(Context context, String msg, String msgText, String alert) {

        PendingIntent notifyInent = PendingIntent.getActivity(context , 0 , new Intent(context , MainActivity.class) , 0);

        NotificationCompat.Builder notiBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.app_logo)
                .setContentTitle(msg)
                .setContentText(msgText)
                .setTicker(alert);


        notiBuilder.setContentIntent(notifyInent);

        notiBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);

        notiBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(1 ,notiBuilder.build());


    }
}
