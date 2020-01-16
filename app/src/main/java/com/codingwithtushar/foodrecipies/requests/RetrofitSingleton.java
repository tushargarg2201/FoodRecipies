package com.codingwithtushar.foodrecipies.requests;

import android.app.Application;
import android.content.Context;

import com.codingwithtushar.foodrecipies.utils.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {


   private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
           .connectTimeout(Constant.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
           .readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
           .writeTimeout(Constant.WRITE_TIMEOUT, TimeUnit.SECONDS)
           .retryOnConnectionFailure(false)
           .build();


   private static Retrofit retrofit = new Retrofit.Builder()
           .baseUrl(Constant.BASE_URL)
           .client(okHttpClient)
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
           .build();

   private static ApiInterface retrofitInstance = retrofit.create(ApiInterface.class);

   public static ApiInterface getRetrofitInstance() {
       return retrofitInstance;
   }
}
