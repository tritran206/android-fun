package com.example.tritran.reciperecyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mTitles;
    private ArrayList<String> mDescriptions;
    private ArrayList<String> mImages;
    private ArrayList<String> mRecipes;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> titles, ArrayList<String> descriptions, ArrayList<String> recipes, ArrayList<String> images) {
        mTitles = titles;
        mDescriptions = descriptions;
        mImages = images;
        mRecipes = recipes;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mImages.get(position))
//                .into(holder.image);

        holder.itemTitle.setText(mTitles.get(position));
        holder.itemDescription.setText(mDescriptions.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mTitles.get(position));

                Intent intent = new Intent(mContext, RecipeDetails.class);
                intent.putExtra("image_url", mImages.get(position));
                intent.putExtra("title", mTitles.get(position));
                intent.putExtra("recipe", mRecipes.get(position));

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemTitle;
        TextView itemDescription;
        LinearLayout parentLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDescription = itemView.findViewById(R.id.itemDescription);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
