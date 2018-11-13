package com.example.tritran.alarmclock;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private Calendar cal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = findViewById(R.id.toggleButton);

        //initialize notification manager
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //initialize alert manager
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //sets alarm to 11:11am
        cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 11);
        cal.set(Calendar.MINUTE, 11);


        //create notification channel to show alerts
        createNotificationChannel();

        //AlarmManager is responsible for delivering the PendingIntent at a specified interval.
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);
        // This PendingIntent delivers an intent letting the app know it is time to update the remaining time in the notification
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //checks state of toggle button and sets alarm accordingly
        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);
        toggleButton.setChecked(alarmUp);

        //sets behavior of togglebutton
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMessage;
                if(isChecked){
                    Log.d(LOG_TAG, "onCheckedChanged: button toggle has been turned on");
                    //calls deliverNotification method that create notification adds settings
                    deliverNotification(MainActivity.this);
                    //sets toast message for when button toggle is turned on
                    toastMessage = getString(R.string.true_toast);
                    if (alarmManager != null) {
                        //sets alarm with alarmanager
                        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), notifyPendingIntent);
                    }

                } else {
                    Log.d(LOG_TAG, "onCheckedChanged: button toggle has been turned off");
                    //turns notifications off
                    if (alarmManager != null) {
                        alarmManager.cancel(notifyPendingIntent);
                    }
                    mNotificationManager.cancelAll();
                    //sets toast message for when button toggle is turned off
                    toastMessage = getString(R.string.false_toast);
                }
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG);
            }
        });
    }

/**
 *    Notification channel
 **/
    public void createNotificationChannel() {
        //create notificationManager object
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //check sdk
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            //initialize notificationChannel object
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, getString(R.string.wish_alarm), NotificationManager.IMPORTANCE_HIGH);
            //notificationChannel settings
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription(getString(R.string.wish_alarm));
            //use notificationmanager to create channel
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
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
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        //notification manager creates notification
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}