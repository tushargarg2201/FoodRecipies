package com.codingwithtushar.foodrecipies.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecipeSearchResponse {

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("recipes")
    @Expose
    private List<recipes> recipesList;

    public List<recipes> getRecipesList() {
        return recipesList;
    }
}
