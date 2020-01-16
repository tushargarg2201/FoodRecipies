package com.codingwithtushar.foodrecipies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.R;
import com.codingwithtushar.foodrecipies.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int RECIPE_TYPE = 1;
    private static final int CATEGORY_TYPE = 2;
    private static final int SEARCH_EXHAUSTED_TYPE = 3;

    private List<recipes> recipesList;
    private OnRecipeListener onRecipeListener;

    public void setRecipeList(List<recipes> recipesList) {
        this.recipesList = recipesList;
        notifyDataSetChanged();
    }

    public List<recipes> getRecipesList() {
        return recipesList;
    }

    public RecipeListAdapter(OnRecipeListener onRecipeListener) {
       this.onRecipeListener = onRecipeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;

        switch (viewType) {
            case RECIPE_TYPE:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_recipe_list_item, viewGroup, false);
                return new RecipeViewHolder(view, onRecipeListener);

            case CATEGORY_TYPE:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_category_list_item, viewGroup, false);
                return new CategoryViewHolder(view, onRecipeListener);

            case SEARCH_EXHAUSTED_TYPE:
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.layout_search_exhausted, viewGroup, false);
                return new SearchExhaustedViewHolder(view);
        }

        return new RecipeViewHolder(view, onRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == RECIPE_TYPE) {
            bindRecipeView(holder, position);
        } else if (itemViewType == CATEGORY_TYPE) {
            bindCategoryView(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(recipesList.get(position).getTitle().equals("EXHAUSTED")) {
            return SEARCH_EXHAUSTED_TYPE;
        }else if(recipesList.get(position).getSocial_rank().equals("-1")){
            return CATEGORY_TYPE;
        } else {
            return RECIPE_TYPE;
        }
    }

    private void bindCategoryView(RecyclerView.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(recipesList.get(position).getImage_url())
                .into(((CategoryViewHolder) holder).categoryImageView);

        ((CategoryViewHolder) holder).categoryTextView.setText(recipesList.get(position).getTitle());
    }

    private void bindRecipeView(RecyclerView.ViewHolder holder, int position) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background);
        requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(recipesList.get(position).getImage_url())
                .into(((RecipeViewHolder) holder).imageView);

        ((RecipeViewHolder) holder).textView.setText(recipesList.get(position).getTitle());
        ((RecipeViewHolder) holder).publisherView.setText(recipesList.get(position).getPublisher());
        String socialRank = recipesList.get(position).getSocial_rank();

        long roundedValue = Math.round(Double.valueOf(socialRank));
        ((RecipeViewHolder) holder).socialView.setText(String.valueOf(roundedValue));
    }

    @Override
    public int getItemCount() {
        if (recipesList != null && recipesList.size() > 0) {
            return recipesList.size();
        }
        return 0;
    }

    public void setQueryExhausted(){
        recipes exhaustedRecipe = new recipes();
        exhaustedRecipe.setTitle("EXHAUSTED");
        if (recipesList != null) {
            recipesList.add(exhaustedRecipe);
        }
        notifyDataSetChanged();
    }

//    public void displaySearchCategories(){
//        List<recipes> categories = new ArrayList<>();
//        for(int i = 0; i< Constant.DEFAULT_SEARCH_CATEGORIES.length; i++){
//            recipes recipe = new recipes();
//            recipe.setTitle(Constant.DEFAULT_SEARCH_CATEGORIES[i]);
//            recipe.setImage_url(Constant.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
//            recipe.setSocial_rank("-1");
//            categories.add(recipe);
//        }
//        recipesList = categories;
//        notifyDataSetChanged();
//    }

}
