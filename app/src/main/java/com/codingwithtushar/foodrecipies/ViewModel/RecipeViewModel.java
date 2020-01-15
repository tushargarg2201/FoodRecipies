package com.codingwithtushar.foodrecipies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.Repository.RecipeRepository;

public class RecipeViewModel extends AndroidViewModel {

    RecipeRepository recipeRepository = RecipeRepository.getRecipeRepositoryInstance();

    public RecipeViewModel(@NonNull Application application) {
        super(application);
    }

    public void searchRecipeById(String recipeId) {
        recipeRepository.searchRecipeById(recipeId);
    }

    public MutableLiveData<recipes> getRecipe(){
        return recipeRepository.getRecipe();
    }
}
