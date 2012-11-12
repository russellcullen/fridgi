package com.fridgi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class BaseIngredient extends Ingredient {
    
    public BaseIngredient(Parcel in) {
        super(in);
        defaultTags = in.createStringArray();
        price = in.readDouble();
        calories = in.readInt();
        upc = in.readLong();
        shelfLife = in.readLong();
        unit = in.readString();
    }
    
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
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringArray(defaultTags);
        dest.writeDouble(price);
        dest.writeInt(calories);
        dest.writeLong(upc);
        dest.writeLong(shelfLife);
        dest.writeString(unit);
    }
    
    public static final Parcelable.Creator<BaseIngredient> CREATOR = new Parcelable.Creator<BaseIngredient>() {
        public BaseIngredient createFromParcel(Parcel in) {
            return new BaseIngredient(in);
        }

        @Override
        public BaseIngredient[] newArray(int size) {
            return new BaseIngredient[size];
        }
    };

}
