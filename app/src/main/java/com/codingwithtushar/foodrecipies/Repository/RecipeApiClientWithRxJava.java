package com.codingwithtushar.foodrecipies.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.codingwithtushar.foodrecipies.Models.RecipeResponse;
import com.codingwithtushar.foodrecipies.Models.RecipeSearchResponse;
import com.codingwithtushar.foodrecipies.Models.recipes;
import com.codingwithtushar.foodrecipies.requests.RetrofitSingleton;
import com.codingwithtushar.foodrecipies.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecipeApiClientWithRxJava {
    private static final String TAG = "RecipeApiClientWithRx";
    private static RecipeApiClientWithRxJava recipeApiClientInstance = null;
    private MutableLiveData<List<recipes>> recipesList;
    private MutableLiveData<recipes> recipe;
    private MutableLiveData<Boolean> isQueryExhausted;

    private RecipeApiClientWithRxJava() {
        recipesList = new MutableLiveData<>();
        recipe = new MutableLiveData<>();
        isQueryExhausted = new MutableLiveData<>();
        //isQueryExhausted.setValue(false);
    }

    public static RecipeApiClientWithRxJava getInstance() {
        if (recipeApiClientInstance == null) {
            recipeApiClientInstance = new RecipeApiClientWithRxJava();
        }
        return recipeApiClientInstance;
    }

    public MutableLiveData<List<recipes>> getRecipesList() {
        return recipesList;
    }

    public MutableLiveData<recipes> getRecipe() {
        return recipe;
    }

    public MutableLiveData<Boolean> getIsQueryExhausted() {
        return isQueryExhausted;
    }

    public void searchRecipeApi(String query, final int pageNumber) {
        Single<RecipeSearchResponse> singleObservable = RetrofitSingleton.getRetrofitInstance()
                .getSearchApiResponseWithRX(Constant.API_KEY, query, pageNumber);

        singleObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<RecipeSearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: called "  + d.isDisposed());

                    }

                    @Override
                    public void onSuccess(RecipeSearchResponse recipeSearchResponse) {
                        List<recipes> localrecipesList =  recipeSearchResponse.getRecipesList();
                        //doneQuery(localrecipesList);
                        Log.d(TAG, "onSuccess: called for SearchRecipeApi and pageNumber is-->" + pageNumber);
                        Log.d(TAG, "onSuccess: localrecipesList size is--->" + localrecipesList.size());
                        if (pageNumber == 1) {
                            if (localrecipesList != null && localrecipesList.size() > 0) {
                                recipesList.setValue(localrecipesList);
                            }
                        } else {
                          List<recipes> currentRecipies = recipesList.getValue();
                          currentRecipies.addAll(localrecipesList);
                          recipesList.setValue(currentRecipies);
                        }
                        Log.d(TAG, "onSuccess: called for SearchRecipeApi and recipesList size is--->" + recipesList.getValue().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ");
                        recipesList.setValue(new ArrayList<recipes>());
                    }
                });

    }

    private void doneQuery(List<recipes> localrecipesList) {
        if (localrecipesList != null && localrecipesList.size() < 30) {
            isQueryExhausted.postValue(true);
        }
    }

    public void searchRecipeById(String recipeId) {
        Single<RecipeResponse> singleObservable = RetrofitSingleton.getRetrofitInstance()
                .getRecipewithRx(Constant.API_KEY, recipeId);

        singleObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RecipeResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: called");
                    }

                    @Override
                    public void onSuccess(RecipeResponse recipeResponse) {
                        Log.d(TAG, "onSuccess: searchRecipeById -->" + recipeResponse);
                        recipe.setValue(recipeResponse.getRecipe());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: searchRecipeById error--->" + e.getMessage());
                    }
                });
    }

}
