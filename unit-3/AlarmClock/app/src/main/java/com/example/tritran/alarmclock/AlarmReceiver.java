package com.example.tritran.alarmclock;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        //creates notification manager to create notifications
        mNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }

    /**
     *      Notification delivery method
     **/
    public void deliverNotification(Context context) {
        //intent to determine how what happens when clicked
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //building notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_wish)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //notification manager creates notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
