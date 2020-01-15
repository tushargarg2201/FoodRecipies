package com.codingwithtushar.foodrecipies.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.Repository.RecipeRepository;

import java.util.List;

public class RecipeListViewModel extends AndroidViewModel {

    MutableLiveData<List<recipes>> recipiesList = new MutableLiveData<>();
    RecipeRepository recipeRepository = RecipeRepository.getRecipeRepositoryInstance();

    public RecipeListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<recipes>> getRecipiesList() {
        recipiesList = recipeRepository.getRecipeListData();
        return recipiesList;
    }

    public void searchRecipeApi(String query, int pageNumber) {
        recipeRepository.searchRecipeApi(query, pageNumber);
    }

    public MutableLiveData<Boolean> isQueryExhausted() {
        return recipeRepository.isQueryExhausted();
    }

}
