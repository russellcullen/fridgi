package com.fridgi.models;

public class Ingredient {
    
    private String mName;
    private String[] mTags;
    private double mPrice;
    private int mCalories;
    private long mUpc;
    private long mShelfLife;
    private String mUnit;
    private double mQuantity;
    
    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }
    public String[] getTags() {
        return mTags;
    }
    public void setTags(String[] mTags) {
        this.mTags = mTags;
    }
    public double getPrice() {
        return mPrice;
    }
    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }
    public int getCalories() {
        return mCalories;
    }
    public void setCalories(int mCalories) {
        this.mCalories = mCalories;
    }
    public long getUpc() {
        return mUpc;
    }
    public void setUpc(long mUpc) {
        this.mUpc = mUpc;
    }
    public long getShelfLife() {
        return mShelfLife;
    }
    public void setShelfLife(long mShelfLife) {
        this.mShelfLife = mShelfLife;
    }
    public String getUnit() {
        return mUnit;
    }
    public void setUnit(String mUnit) {
        this.mUnit = mUnit;
    }
    public double getQuantity() {
        return mQuantity;
    }
    public void setQuantity(double mQuantity) {
        this.mQuantity = mQuantity;
    }

}
