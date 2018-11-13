package com.example.tritran.standup;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton mToggleButton;
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize AlarmManager
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        //intent for notification
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mToggleButton = findViewById(R.id.alarmToggle);
        mToggleButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String toastMessage;
                        if(isChecked) {
                            long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                            long triggerTime = SystemClock.elapsedRealtime() + repeatInterval;

                            //If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                            if (alarmManager != null) {
                                alarmManager.setInexactRepeating
                                        (AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                                triggerTime, repeatInterval, notifyPendingIntent);
                            }
                            //Set the toast message for the "on" case.
                            toastMessage = "Stand Up Alarm On!";
                        } else {
                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            //Cancel notification if the alarm is turned off
                            mNotificationManager.cancelAll();
                            //Set the toast message for the "off" case.
                            toastMessage = "Stand Up Alarm Off!";
                        }

                        //show a toast to say if alarm is on or off
                        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG);
                    }
                }
        );

        //checks for state of alarm and sets if to on if needed
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        mToggleButton.setChecked(alarmUp);

        createNotificationChannel();
    }

    public void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, MainActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, contentIntent, PendingIntent.FLAG_ONE_SHOT);

        //notification settings
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_stand_up)
                .setContentTitle(getString(R.string.stand_alert))
                .setContentText(getString(R.string.notification_builder_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //manager creates notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Creates a Notification channel, for OREO and higher.
     */
    public void createNotificationChannel() {

        // Create a notification manager object.
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notificationChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void showToast(View view) {
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O){


            Toast.makeText(this, alarmManager.getNextAlarmClock().toString(), Toast.LENGTH_LONG );
        }
    }
}
