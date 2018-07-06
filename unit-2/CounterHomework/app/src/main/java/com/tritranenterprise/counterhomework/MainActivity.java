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
        mTextView = findViewById(R.id.count_view);

        if (savedInstanceState != null) {
            mCount = savedInstanceState.getInt("count text");
            mTextView.setText(Integer.toString(mCount));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count text", mCount);
    }

    public void incrementCount(View view) {
        mCount = mCount + 100;
        mTextView.setText(Integer.toString(mCount));
    }


}
