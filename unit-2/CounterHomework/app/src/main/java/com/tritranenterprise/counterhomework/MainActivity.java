package com.tritranenterprise.counterhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int mCount = 0;
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize textview by finding its it by its id
        mTextView = findViewById(R.id.count_view);

        //checks to see if bundle is null
        if (savedInstanceState != null) {
            //use key created to retrieve Int data stored in bundle
            mCount = savedInstanceState.getInt("count text");
            //sets text to view
            mTextView.setText(Integer.toString(mCount));
        }
    }

    //overides onSaveInstanceState to store data
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //stores int data to bundle with newly given key
        outState.putInt("count text", mCount);
    }

    public void incrementCount(View view) {
        mCount = mCount + 100;
        mTextView.setText(Integer.toString(mCount));
    }


}
