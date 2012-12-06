package com.fridgi.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements 
    Parcelable, Comparable<Ingredient> {
    
    private String name;
    private double quantity;
    private String unit;
    
    public String getName() {
        return name;
    }
    public void setName(String mName) {
        this.name = mName;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double mQuantity) {
        this.quantity = mQuantity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String mUnit) {
        this.unit = mUnit;
    }
    
    public Ingredient(Parcel in) {
        name = in.readString();
        quantity = in.readDouble();
        unit = in.readString();
    }
    
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(quantity);
        dest.writeString(unit);
    }
    @Override
    public int compareTo(Ingredient another) {
        return name.compareTo(another.getName());
    }

}
