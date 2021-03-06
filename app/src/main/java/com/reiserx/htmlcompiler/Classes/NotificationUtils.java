package com.reiserx.htmlcompiler.Classes;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.reiserx.htmlcompiler.Activities.ProjectList;
import com.reiserx.htmlcompiler.R;


public class NotificationUtils {

    public void sendNotification(Context context, String title, String content, int id) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channel_id = "testtrace365";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant")
            NotificationChannel notificationChannel = new NotificationChannel(channel_id, "Cloud messaging", NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("service");
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 5000, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notify_bulder = new NotificationCompat.Builder(context, channel_id);
        notify_bulder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_baseline_html_24)
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .setContentText(content)
                .setSilent(true)
                .setContentTitle(title)
                .setContentInfo("info");
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, ProjectList.class), PendingIntent.FLAG_UPDATE_CURRENT);
        notify_bulder.setContentIntent(contentIntent);


        // Gets an instance of the NotificationManager service
        notificationManager.notify(id, notify_bulder.build());
    }
}
