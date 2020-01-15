package com.codingwithtushar.foodrecipies.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.codingwithtushar.foodrecipies.R;

public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView textView;
    TextView publisherView;
    TextView socialView;
    AppCompatImageView imageView;
    OnRecipeListener onRecipeListener;
    public RecipeViewHolder(@NonNull View itemView, OnRecipeListener onRecipeListener) {
        super(itemView);
        textView = itemView.findViewById(R.id.recipe_title);
        publisherView = itemView.findViewById(R.id.recipe_publisher);
        socialView = itemView.findViewById(R.id.recipe_social_score);
        imageView = itemView.findViewById(R.id.recipe_image);
        this.onRecipeListener = onRecipeListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       onRecipeListener.onRecipeClick(getAdapterPosition());
    }
}
