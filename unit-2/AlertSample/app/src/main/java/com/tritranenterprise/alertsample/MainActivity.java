package com.tritranenterprise.alertsample;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        //set dialog title
        myAlertBuilder.setTitle(R.string.alert_title);
        //set the dialog message
        myAlertBuilder.setMessage(R.string.alert_message);
        //add the buttons
        myAlertBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //user clicked OK button
                Toast.makeText(getApplicationContext(), R.string.pressed_ok, Toast.LENGTH_SHORT).show();
            }
        });

        myAlertBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //user cancelled dialog
                Toast.makeText(getApplicationContext(), R.string.pressed_cancel, Toast.LENGTH_SHORT).show();
            }
        });

        //create and show the alertdialog
        myAlertBuilder.show();

    }
}
