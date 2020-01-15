package com.codingwithtushar.foodrecipies.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codingwithtushar.foodrecipies.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CircleImageView categoryImageView;
    TextView categoryTextView;
    private OnRecipeListener listener;

    public CategoryViewHolder(@NonNull View itemView, OnRecipeListener listener) {
        super(itemView);
        categoryImageView = itemView.findViewById(R.id.category_image);
        categoryTextView = itemView.findViewById(R.id.category_title);
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
       //listener.onCategoryClick();
    }
}
