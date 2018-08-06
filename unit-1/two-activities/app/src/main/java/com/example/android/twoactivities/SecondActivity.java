package com.example.android.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> 347a73abb53411364ca69f9137d897638bf37038
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
<<<<<<< HEAD

    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    private EditText mReply;
=======
    //creates final string key for intent extra
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    //holds EditText object
    private EditText mReply;
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();
>>>>>>> 347a73abb53411364ca69f9137d897638bf37038

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
<<<<<<< HEAD

        // Initialize view variables.
        mReply = (EditText) findViewById(R.id.editText_second);

        // Get the intent that launched this activity, and the message in
        // the intent extra.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Put that message into the text_message TextView
        TextView textView = (TextView) findViewById(R.id.text_message);
        if (textView != null) {
            textView.setText(message);
        }
    }

    public void returnReply(View view) {
        // Get the reply message from the edit text.
        String reply = mReply.getText().toString();

        // Create a new intent for the reply, add the reply message to it as an extra,
        // set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);
=======
        //creates intent object
        Intent intent = getIntent();
        //retrieves message data using the a key and intent object
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        //gets textView object to display message
        TextView textView = findViewById(R.id.text_message);
        //sets string message to textview
        textView.setText(message);
        //assigns edittext object to variable using its id
        mReply = findViewById(R.id.editText_second);
        Log.d(LOG_TAG, "onCreate2");

    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart2");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume2");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy2");
    }


    public void returnReply(View view) {
        //gets string from object
        String reply = mReply.getText().toString();
        Log.d(LOG_TAG, "End SecondActivity");
        //creates intent to reply message
        Intent replyIntent = new Intent();
        //adds extra to newly created intent
        replyIntent.putExtra(EXTRA_REPLY, reply);
        //sets result to OK
        setResult(RESULT_OK,replyIntent);
        //closes activity
>>>>>>> 347a73abb53411364ca69f9137d897638bf37038
        finish();
    }
}
