package com.fridgi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fridge {
    
    private String name;
    private List<FridgeIngredient> ingredients;
    @SerializedName("favorite_recipes") private List<Recipe> favoriteRecipes;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<FridgeIngredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<FridgeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
    public List<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }
    public void setFavoriteRecipes(List<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
    
}
