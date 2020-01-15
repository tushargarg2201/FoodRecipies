package com.codingwithtushar.foodrecipies.requests;

import com.codingwithtushar.foodrecipies.Models.RecipeResponse;
import com.codingwithtushar.foodrecipies.Models.RecipeSearchResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/search")
    Single<RecipeSearchResponse> getSearchApiResponseWithRX(
            @Query("api_key") String api_key,
            @Query("q") String query,
            @Query("page") int page
    );

    @GET("/api/search")
    Call<RecipeSearchResponse> getSearchApiResponse(
            @Query("api_key") String api_key,
            @Query("q") String query,
            @Query("page") int page
    );

    // GET RECIPE REQUEST
    @GET("/api/get")
    Call<RecipeResponse> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );

    @GET("/api/get")
    Single<RecipeResponse> getRecipewithRx(
            @Query("key") String key,
            @Query("rId") String recipe_id
    );
}
