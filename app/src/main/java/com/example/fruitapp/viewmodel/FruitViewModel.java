package com.example.fruitapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fruitapp.fruits_list.api.APIService;
import com.example.fruitapp.fruits_list.api.JSONResponse;
import com.example.fruitapp.fruits_list.api.RetrofitInstance;
import com.example.fruitapp.model.FruitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class FruitViewModel extends ViewModel {

    private MutableLiveData<List<FruitModel>> fruitList;
    private MutableLiveData<FruitModel> fruitSelected;

    public FruitViewModel(){
        fruitList = new MutableLiveData<>();
        fruitSelected = new MutableLiveData<>();
    }

    public MutableLiveData<List<FruitModel>> getFruitsListObserver() {
        return fruitList;
    }

    public MutableLiveData<FruitModel> getFruitObserver() {
        return fruitSelected;
    }

    public void setFruitObserver(FruitModel fruitModel) {
        this.fruitSelected.setValue(fruitModel);
    }

    public void makeApiCall() {

        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<JSONResponse> call = apiService.getJSON();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                fruitList.postValue(response.body().getFruits());
             }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                fruitList.postValue(null);
            }
        });
    }
}
