package com.fridgi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Fridge {
    
    private String name;
    private List<FridgeIngredient> ingredients;
    @SerializedName("favorite_recipes") private List<Recipe> favoriteRecipes;
    @SerializedName("_id") private ObjectID id;
    @SerializedName("grocery_list") private List<RecipeIngredient> groceryList;
    
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
    public ObjectID getId() {
        return id;
    }
    public void setId(ObjectID id) {
        this.id = id;
    }
    public List<RecipeIngredient> getGroceryList() {
        return groceryList;
    }
    public void setGroceryList(List<RecipeIngredient> groceryList) {
        this.groceryList = groceryList;
    }
    
}
