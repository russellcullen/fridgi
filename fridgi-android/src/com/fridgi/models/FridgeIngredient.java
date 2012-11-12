package com.fridgi.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class FridgeIngredient extends Ingredient {
    
    public FridgeIngredient(Parcel in) {
        super(in);
        insertTime = in.readDouble();
        count = in.readInt();
        tags = in.createStringArray();
    }
    
    //    private String ingredient;
    @SerializedName("insert_time") private double insertTime;
    private int count;
    private String[] tags;
    
//    public String getIngredient() {
//        return ingredient;
//    }
//    public void setIngredient(String ingredient) {
//        this.ingredient = ingredient;
//    }
    public double getInsertTime() {
        return insertTime;
    }
    public void setInsertTime(double insertTime) {
        this.insertTime = insertTime;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(insertTime);
        dest.writeInt(count);
        dest.writeStringArray(tags);
    }
}
