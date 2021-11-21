package com.example.fruitapp.fruits_list.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class RetrofitInstance {

    public static String BASE_URL = "https://dev-api.com/";//fruitsBT/getFruits

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {

        if(retrofit == null ) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}