package com.codingwithtushar.foodrecipies.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.codingwithtushar.foodrecipies.Models.recipes;

import java.util.List;

public class RecipeRepository {
    private static RecipeRepository recipeRepositoryInstance = null;
    RecipeApiClient recipeApiClient = null;
    RecipeApiClientWithRxJava recipeApiClientWithRxJava = null;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<recipes>> mRecipes = new MediatorLiveData<>();

    private RecipeRepository() {
        recipeApiClient = RecipeApiClient.getInstance();
        recipeApiClientWithRxJava = RecipeApiClientWithRxJava.getInstance();
        initMediators();
    }

    private void initMediators() {
        LiveData<List<recipes>>recipeListApiSource =recipeApiClientWithRxJava.getRecipesList();
        mRecipes.addSource(recipeListApiSource, new Observer<List<recipes>>() {
            @Override
            public void onChanged(List<recipes> recipes) {
                if (recipes != null) {
                    mRecipes.setValue(recipes);
                    doneQuery(recipes);
                } else {
                    doneQuery(null);
                }
            }
        });
    }

    private void doneQuery(List<recipes> recipes) {
        if (recipes != null && recipes.size() %30 != 0) {
            mIsQueryExhausted.setValue(true);
        }
    }

    public static RecipeRepository getRecipeRepositoryInstance() {
        if (recipeRepositoryInstance == null) {
            recipeRepositoryInstance = new RecipeRepository();
        }
        return recipeRepositoryInstance;
    }

    public MutableLiveData<List<recipes>> getRecipeListData() {
        //return recipeApiClientWithRxJava.getRecipesList();
        return mRecipes;
    }

    public void searchRecipeApi(String query, int pageNumber) {
        recipeApiClientWithRxJava.searchRecipeApi(query, pageNumber);
    }

    public MutableLiveData<recipes> getRecipe() {
        return recipeApiClientWithRxJava.getRecipe();
    }

    public void searchRecipeById(String recipeId) {
        recipeApiClientWithRxJava.searchRecipeById(recipeId);

    }

    public MutableLiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }
}
