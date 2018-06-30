package com.example.android.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    //creates final string key for intent extra
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    //holds EditText object
    private EditText mReply;
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
        finish();
    }
}
