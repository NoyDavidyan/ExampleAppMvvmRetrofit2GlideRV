package com.example.fruitapp.fruits_list.api;

import com.example.fruitapp.model.FruitModel;

import java.util.ArrayList;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class JSONResponse {

    private ArrayList<FruitModel> fruits;

    public ArrayList<FruitModel> getFruits() {
        return this.fruits;
    }
}
