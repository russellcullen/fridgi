package com.fridgi.models;

import com.google.gson.annotations.SerializedName;


public class BaseIngredient extends Ingredient {
    
    @SerializedName("default_tags") private String[] defaultTags;
    private double price;
    private int calories;
    private long upc;
    private long shelfLife;
    private String unit;
    
    public String[] getDefaultTags() {
        return defaultTags;
    }
    public void setDefaultTags(String[] mTags) {
        this.defaultTags = mTags;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double mPrice) {
        this.price = mPrice;
    }
    public int getCalories() {
        return calories;
    }
    public void setCalories(int mCalories) {
        this.calories = mCalories;
    }
    public long getUpc() {
        return upc;
    }
    public void setUpc(long mUpc) {
        this.upc = mUpc;
    }
    public long getShelfLife() {
        return shelfLife;
    }
    public void setShelfLife(long mShelfLife) {
        this.shelfLife = mShelfLife;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String mUnit) {
        this.unit = mUnit;
    }

}
