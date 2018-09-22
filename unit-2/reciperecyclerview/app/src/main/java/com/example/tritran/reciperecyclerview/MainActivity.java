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
    private ArrayList<String> mDescriptions = new ArrayList<>();
    private ArrayList<String> mRecipes = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private RecyclerView parentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started");

        initData();

//        parentLayout = findViewById(R.id.recyclerview);
//        parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openRecipeDetails();
//            }
//        });
    }

    public void openRecipeDetails() {
        Intent recipeDetails = new Intent(this, RecipeDetails.class);
        startActivity(recipeDetails);
    }

    private void initData() {
        Log.d(TAG, "initData : init data");
        mTitles.add("Crunchwrap supremes");
        mImages.add("https://i.redd.it/btrjwyh81un11.jpg");
        mRecipes.add("Beef Mixture\n" +
                "\n" +
                "1-2 lbs Ground Beef\n" +
                "\n" +
                "1 Habanero Pepper, finely chopped, deseeded\n" +
                "\n" +
                "4 Cloves Garlic, finely chopped\n" +
                "\n" +
                "1 Tbsp Olive Oil\n" +
                "\n" +
                "Spice (I dont measure when I use spices, but this included Cumin, Coriander, Chili Powder, Paprika, Cayenne, Oregano, Salt and Pepper)\n" +
                "\n" +
                "Cheese Sauce\n" +
                "\n" +
                "2 Tbsp Butter, unsalted\n" +
                "\n" +
                "2 Tbsp All purpose Flour\n" +
                "\n" +
                "1 Bottle of your favorite dark lager/ale/stout\n" +
                "\n" +
                "Milk\n" +
                "\n" +
                "~1 shitload of cheese (for this batch, I used Monterey Jack)\n" +
                "\n" +
                "1 Tbsp Paprika\n" +
                "\n" +
                "1/2 Tbsp Mustard Powder\n" +
                "\n" +
                "Everything Else\n" +
                "\n" +
                "Large \"Burrito\" Size Flour Tortillas\n" +
                "\n" +
                "Small (~6\" diameter) Corn Tortillas\n" +
                "\n" +
                "Sour cream\n" +
                "\n" +
                "Romaine lettuce, shaved SUPER thin\n" +
                "\n" +
                "Onion\n" +
                "\n" +
                "Tomato\n" +
                "\n" +
                "Juice from 1 Lime\n" +
                "\n" +
                "Cilantro");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data2");
        mTitles.add("Wontons With Chili Oil");
        mImages.add("https://i.redd.it/ayqnrg2idqn11.jpg");
        mRecipes.add("In a pan I put maybe 1 1/2 cups of vegetable oil, 5 star anise, 7 tablespoons Sichuan peppercorns, one cinnamon stick, 5 cardamon pods, 3 bay leaves, 4 cloves smashed garlic, an inch stick of ginger and 20 dried Chinese chilies and 6 Thai chilies. Cook them over low heat for at least half an hour. The ginger and garlic should be lightly bubbling in the oil, but not hot enough that they burn.\n" +
                "\n" +
                "I deseeded a bunch of Chinese chilis and ground them up in a spice grinder and set them aside. After at least half an hour pour the oil through a strainer onto the ground up Chinese chilies and let sit for a bit. It will keep in your fridge for about 2 months.\n" +
                "\n" +
                "The sauce for the wontons was 6 tbs chili oil, 1 1/2 tsp of the ground up chilies from the oil, 2 tbs soy, 1 tbs dark soy, 2 tsp sugar and 1 1/2 tsp dark vinegar. It's nice because the chili oil sits on top and then the bottom of the wonton soaks up the soy/dark vinegar");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data3");
        mTitles.add("Roasted Green Chile and Bacon Macaroni and Cheese");
        mImages.add("https://i.redd.it/t7gsgr6ir0l11.jpg");
        mRecipes.add("4 tbs butter\n" +
                "\n" +
                "4 tbs flour\n" +
                "\n" +
                "3 cups whole milk, warmed\n" +
                "\n" +
                "1 tsp salt\n" +
                "\n" +
                "black pepper to taste\n" +
                "\n" +
                "dash of nutmeg\n" +
                "\n" +
                "8 oz. Colby cheese, grated\n" +
                "\n" +
                "8 oz. sharp Cheddar, grated\n" +
                "\n" +
                "1 tsp nice mustard\n" +
                "\n" +
                "1 cup diced roasted Hatch chiles (or other similar chiles if you can’t find them)\n" +
                "\n" +
                "Cooked bacon (maybe 6 strips or so? I didn’t go bacon crazy, so use more if you fancy)\n" +
                "\n" +
                "16 oz dried macaroni\n" +
                "\n" +
                "1 jalapeño, seeded and finely chopped");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data4");
        mTitles.add("Crawfish Fettuccine");
        mImages.add("https://i.redd.it/79unpvuxpvl11.jpg");
        mRecipes.add("1/4 cup butter\n" +
                "\n" +
                "1 cup diced onion\n" +
                "\n" +
                "1/2 cup diced green bell pepper\n" +
                "\n" +
                "1/4 cup diced celery\n" +
                "\n" +
                "2 cloves garlic, chopped\n" +
                "\n" +
                "5 Tbsp flour\n" +
                "\n" +
                "3/4 cup half and half\n" +
                "\n" +
                "2 lbs frozen crawfish tails, thawed (with liquid)\n" +
                "\n" +
                "1 Tbsp fresh parsley, chopped\n" +
                "\n" +
                "1 lb Velveeta cheese, cubed\n" +
                "\n" +
                "1 lb fettuccine, cooked and drained (save some pasta water)\n" +
                "\n" +
                "Cajun Seasoning to taste");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data5");
        mTitles.add("Taiwanese Beef wrap");
        mImages.add("https://i.redd.it/uj9lau9ncrn11.jpg");
        mRecipes.add("1/4 cup butter\n" +
                "\n" +
                "1 cup diced onion\n" +
                "\n" +
                "1/2 cup diced green bell pepper\n" +
                "\n" +
                "1/4 cup diced celery\n" +
                "\n" +
                "2 cloves garlic, chopped\n" +
                "\n" +
                "5 Tbsp flour\n" +
                "\n" +
                "3/4 cup half and half\n" +
                "\n" +
                "2 lbs frozen crawfish tails, thawed (with liquid)\n" +
                "\n" +
                "1 Tbsp fresh parsley, chopped\n" +
                "\n" +
                "1 lb Velveeta cheese, cubed\n" +
                "\n" +
                "1 lb fettuccine, cooked and drained (save some pasta water)\n" +
                "\n" +
                "Cajun Seasoning to taste");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data6");
        mTitles.add("These mini garlic croissants");
        mImages.add("https://i.redd.it/f2rt446h5tn11.jpg");
        mRecipes.add("1/4 cup butter\n" +
                "\n" +
                "1 cup diced onion\n" +
                "\n" +
                "1/2 cup diced green bell pepper\n" +
                "\n" +
                "1/4 cup diced celery\n" +
                "\n" +
                "2 cloves garlic, chopped\n" +
                "\n" +
                "5 Tbsp flour\n" +
                "\n" +
                "3/4 cup half and half\n" +
                "\n" +
                "2 lbs frozen crawfish tails, thawed (with liquid)\n" +
                "\n" +
                "1 Tbsp fresh parsley, chopped\n" +
                "\n" +
                "1 lb Velveeta cheese, cubed\n" +
                "\n" +
                "1 lb fettuccine, cooked and drained (save some pasta water)\n" +
                "\n" +
                "Cajun Seasoning to taste");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        Log.d(TAG, "initData : init data7");
        mTitles.add("Spicy miso ramen with pork belly");
        mImages.add("https://i.redd.it/r22k5jp1lon11.jpg");
        mRecipes.add("1/4 cup butter\n" +
                "\n" +
                "1 cup diced onion\n" +
                "\n" +
                "1/2 cup diced green bell pepper\n" +
                "\n" +
                "1/4 cup diced celery\n" +
                "\n" +
                "2 cloves garlic, chopped\n" +
                "\n" +
                "5 Tbsp flour\n" +
                "\n" +
                "3/4 cup half and half\n" +
                "\n" +
                "2 lbs frozen crawfish tails, thawed (with liquid)\n" +
                "\n" +
                "1 Tbsp fresh parsley, chopped\n" +
                "\n" +
                "1 lb Velveeta cheese, cubed\n" +
                "\n" +
                "1 lb fettuccine, cooked and drained (save some pasta water)\n" +
                "\n" +
                "Cajun Seasoning to taste");
        mDescriptions.add("This sandwich is stunningly delicious and tastes as good as it is healthy. Any greens of choice will work. The lemon adds an almost sweet taste. Be generous with it");

        initRecyclerView();
    }

    private void initRecyclerView() {
     Log.d(TAG, "initRecyclerView : init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTitles, mDescriptions, mRecipes, mImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
