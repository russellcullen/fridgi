package com.fridgi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {
    
    private String name;
    private List<RecipeIngredient> ingredients;
    private String[] instructions;
    private float rating;
    private String[] tags;
    @SerializedName("last_used") private long lastUsed;
    @SerializedName("serving_size") private float servingSize;
    @SerializedName("can_cook") private boolean canCook;
    private int relevance;
    
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
    public long getLastUsed() {
        return lastUsed;
    }
    public void setLastUsed(long lastUsed) {
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
}
