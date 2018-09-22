package com.example.tritran.reciperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecipeDetails extends AppCompatActivity {

    private static final String TAG = "RecipeDetails";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("title") && getIntent().hasExtra("recipe")) {
            Log.d(TAG, "getIncomingIntent: found extras");

            String imageURL = getIntent().getStringExtra("image_url");
            String title = getIntent().getStringExtra("title");
            String recipe = getIntent().getStringExtra("recipe");

            setData(title, recipe, imageURL);
        }
    }

    private void setData(String title, String recipe, String imageURL) {
        Log.d(TAG, "setImage: setting the image, title, and recipe");

        TextView textViewTitle = findViewById(R.id.recipe_title);
        textViewTitle.setText(title);

        TextView textViewIngredients = findViewById(R.id.ingredients);
        textViewIngredients.setText(recipe);

        ImageView imageView = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageURL)
                .into(imageView);
    }
}
