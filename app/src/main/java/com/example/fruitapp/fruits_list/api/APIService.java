package com.example.fruitapp.fruits_list.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public interface APIService {

    @GET("fruitsBT/getFruits")
    Call<JSONResponse> getJSON();
}
