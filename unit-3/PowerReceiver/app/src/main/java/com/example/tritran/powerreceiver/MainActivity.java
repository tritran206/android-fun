package com.example.tritran.powerreceiver;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private CustomReceiver mReceiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create an intent filter with Intent actions
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter);

        //register local broadcast
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver,
                        new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }


    @Override
    protected void onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mReceiver);
        super.onDestroy();

        //unregister local broadcast
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
