package com.example.fruitapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Noy davidyan on 02/11/2021.
 */

public class FruitModel {

    @SerializedName("name")
    private String nameStr;

    @SerializedName("image")
    private String imageUrlStr;

    @SerializedName("description")
    private String descriptionStr;

    @SerializedName("price")
    private int priceInt;

    public FruitModel(String nameStr, String imageUrlStr, String descriptionStr, int priceInt) {
        this.nameStr = nameStr;
        this.imageUrlStr = imageUrlStr;
        this.descriptionStr = descriptionStr;
        this.priceInt = priceInt;
    }

    public String getNameStr() {
        return nameStr;
    }

    public void setNameStr(String nameStr) {
        this.nameStr = nameStr;
    }

    public String getImageUrlStr() {
        return imageUrlStr;
    }

    public void setImageUrlStr(String imageUrlStr) {
        this.imageUrlStr = imageUrlStr;
    }

    public String getDescriptionStr() {
        return descriptionStr;
    }

    public void setDescriptionStr(String descriptionStr) {
        this.descriptionStr = descriptionStr;
    }

    public int getPriceInt() {
        return priceInt;
    }

    public void setPriceInt(int priceInt) {
        this.priceInt = priceInt;
    }
}
