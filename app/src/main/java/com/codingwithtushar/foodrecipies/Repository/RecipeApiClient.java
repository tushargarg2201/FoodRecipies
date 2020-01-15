package com.codingwithtushar.foodrecipies.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.codingwithtushar.foodrecipies.Models.RecipeSearchResponse;
import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.requests.RetrofitSingleton;
import com.codingwithtushar.foodrecipies.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeApiClient {

    private static final String TAG = "RecipeApiClient";
    private static RecipeApiClient recipeApiClientInstance = null;
    private MutableLiveData<List<recipes>> recipesList;

    private RecipeApiClient() {
        recipesList = new MutableLiveData<>();
    }

    public static RecipeApiClient getInstance() {
        if (recipeApiClientInstance == null) {
            recipeApiClientInstance = new RecipeApiClient();
        }
        return recipeApiClientInstance;
    }

    public MutableLiveData<List<recipes>> getRecipesList() {
        recipesList = startNetworkRequest();
        return recipesList;
    }

    private MutableLiveData<List<recipes>> startNetworkRequest() {
        RetrofitSingleton.getRetrofitInstance()
                .getSearchApiResponse(Constant.API_KEY, "chicken", 1)
                .enqueue(new Callback<RecipeSearchResponse>() {
                    @Override
                    public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                        Log.d(TAG, "onResponse: called " + response.body());
                        if (response.code() == 200) {
                            RecipeSearchResponse responseBody = response.body();
                            List<recipes> localrecipesList =  responseBody.getRecipesList();
                            if (localrecipesList != null && localrecipesList.size() > 0) {
                                for (recipes recipe : localrecipesList) {
                                    Log.d(TAG, "onResponse: recipe title is---" + recipe.getTitle());
                                }
                                recipesList.setValue(localrecipesList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: called");
                    }
                });

         return recipesList;
    }
}
