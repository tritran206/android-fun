package com.example.tritran.reciperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private RecyclerView parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        initData();

        parentLayout = findViewById(R.id.recyclerview);
        parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeDetails();
            }
        });
    }

    public void openRecipeDetails() {
        Intent recipeDetails = new Intent(this, RecipeDetails.class);
        startActivity(recipeDetails);
    }

    private void initData() {
        Log.d(TAG, "initData : init data");
        mTitles.add("Kale/Lemon Sandwiches");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data2");
        mTitles.add("Mango-Lime Bean Salad");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data3");
        mTitles.add("Sweet Potato and Lentil Soup with Shiitake Mushrooms");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        mTitles.add("Lime Mousse");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        mTitles.add("Broiled Tilapia Parmesan");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        mTitles.add("Lasagna");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        mTitles.add("Spaghetti");
        mDescription.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        initRecyclerView();
    }

    private void initRecyclerView() {
     Log.d(TAG, "initRecyclerView : init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTitles, mDescription);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
