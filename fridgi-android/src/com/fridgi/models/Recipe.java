package com.fridgi.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    
    private String name;
    private List<RecipeIngredient> ingredients;
    private String[] instructions;
    private float rating;
    private String[] tags;
    @SerializedName("last_used") private double lastUsed;
    @SerializedName("serving_size") private float servingSize;
    @SerializedName("can_cook") private boolean canCook;
    private int relevance;
    @SerializedName("_id") private ObjectID id;
    
    public ObjectID getId() {
        return id;
    }
    public void setId(ObjectID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
    public String[] getInstructions() {
        return instructions;
    }
    public void setInstructions(String[] instructions) {
        this.instructions = instructions;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String[] getTags() {
        return tags;
    }
    public void setTags(String[] tags) {
        this.tags = tags;
    }
    public double getLastUsed() {
        return lastUsed;
    }
    public void setLastUsed(double lastUsed) {
        this.lastUsed = lastUsed;
    }
    public float getServingSize() {
        return servingSize;
    }
    public void setServingSize(float servingSize) {
        this.servingSize = servingSize;
    }
    public boolean isCanCook() {
        return canCook;
    }
    public void setCanCook(boolean canCook) {
        this.canCook = canCook;
    }
    public int getRelevance() {
        return relevance;
    }
    public void setRelevance(int relevance) {
        this.relevance = relevance;
    }
    
    public Recipe(Parcel in) {
        name = in.readString();
        ingredients = new ArrayList<RecipeIngredient>();
        in.readTypedList(ingredients, RecipeIngredient.CREATOR);
        instructions = in.createStringArray();
        rating = in.readFloat();
        tags = in.createStringArray();
        lastUsed = in.readDouble();
        servingSize = in.readFloat();
        canCook = in.readInt() == 1;
        relevance = in.readInt();
        id = in.readParcelable(ObjectID.class.getClassLoader());
    }
    
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override 
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(ingredients);
        dest.writeStringArray(instructions);
        dest.writeFloat(rating);
        dest.writeStringArray(tags);
        dest.writeDouble(lastUsed);
        dest.writeFloat(servingSize);
        dest.writeInt(canCook ? 1 : 0);
        dest.writeInt(relevance);
        dest.writeParcelable(id, flags);
    }
    
    
    @Override
    public int describeContents() {
        return 0;
    }
}
