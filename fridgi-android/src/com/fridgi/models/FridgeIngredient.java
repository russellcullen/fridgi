package com.fridgi.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class FridgeIngredient extends Ingredient {
    
    @SerializedName("insert_time") private double insertTime;
    private int count;
    private String[] tags;
    private ObjectID ingredient;
    
    public FridgeIngredient(Parcel in) {
        super(in);
        insertTime = in.readDouble();
        count = in.readInt();
        tags = in.createStringArray();
        ingredient = in.readParcelable(ObjectID.class.getClassLoader());
    }
    
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
    
    public ObjectID getIngredient() {
        return ingredient;
    }

    public void setIngredient(ObjectID ingredient) {
        this.ingredient = ingredient;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeDouble(insertTime);
        dest.writeInt(count);
        dest.writeStringArray(tags);
        dest.writeParcelable(ingredient, flags);
    }


}
