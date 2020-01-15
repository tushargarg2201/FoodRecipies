package com.codingwithtushar.foodrecipies.requests;

import com.codingwithtushar.foodrecipies.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

   private static Retrofit retrofit = new Retrofit.Builder()
           .baseUrl(Constant.BASE_URL)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build();

   private static ApiInterface retrofitInstance = retrofit.create(ApiInterface.class);

   public static ApiInterface getRetrofitInstance() {
       return retrofitInstance;
   }
}
