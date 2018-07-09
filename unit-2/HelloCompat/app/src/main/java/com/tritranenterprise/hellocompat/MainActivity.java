package com.tritranenterprise.hellocompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mHelloTextView;
    private String[] mColorArray = {"red", "pink", "purple", "deep_purple",
            "indigo", "blue", "light_blue", "cyan", "teal", "green",
            "light_green", "lime", "yellow", "amber", "orange", "deep_orange",
            "brown", "grey", "blue_grey", "black"
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize main textview
        mHelloTextView = findViewById(R.id.hello_textview);
        //restores saved instance state for color
        if (savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    public void changeColor(View view) {
        //random object that creates random numbers
        Random random = new Random();
        // chooses a random int 0-19 and use that to get color at that index from mColorArray
        String colorName = mColorArray[random.nextInt(20)];
        //The getResources() method gets all the resources for your app.
        // The getIdentifier() method looks up the color name (the string) in the color resources ("color") for the current package name.
        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());
        //The getResources() method gets the set of resources for your app
        // the getColor() method retrieves a specific color from those resources by the ID of the color name.
        int colorRes = getResources().getColor(colorResourceName);
        mHelloTextView.setTextColor(colorRes);
    }
}
