package com.example.android.twoactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    //creates final string key for intent extra
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";
    //holds EditText object
    private EditText mReply;

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

    }

    public void returnReply(View view) {
        //gets string from object
        String reply = mReply.getText().toString();
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
