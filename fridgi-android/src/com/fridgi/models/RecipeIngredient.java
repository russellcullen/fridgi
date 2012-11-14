package com.fridgi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class RecipeIngredient extends Ingredient {
    
    private String unit;
    private ObjectID ingredient;
    
    public RecipeIngredient(Parcel in) {
        super(in);
        unit = in.readString();
        ingredient = in.readParcelable(ObjectID.class.getClassLoader());
    }
    
    public static final Parcelable.Creator<RecipeIngredient> CREATOR = new Parcelable.Creator<RecipeIngredient>() {
        public RecipeIngredient createFromParcel(Parcel in) {
            return new RecipeIngredient(in);
        }

        @Override
        public RecipeIngredient[] newArray(int size) {
            return new RecipeIngredient[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(unit);
        dest.writeParcelable(ingredient, flags);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ObjectID getIngredient() {
        return ingredient;
    }

    public void setIngredient(ObjectID ingredient) {
        this.ingredient = ingredient;
    }
    
}
