package com.fridgi.util;

import com.fridgi.models.BaseIngredient;
import com.fridgi.models.Fridge;
import com.fridgi.models.Recipe;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Parsers {
    
    public static BaseIngredient parseBaseIngredient(JSONObject obj) {
        return new Gson().fromJson(obj.toString(), BaseIngredient.class);
    }
    
    public static Recipe parseRecipe(JSONObject obj) {
        return new Gson().fromJson(obj.toString(), Recipe.class);
    }
    
    public static Fridge parseFridge(JSONObject obj) {
        return new Gson().fromJson(obj.toString(), Fridge.class);
    }
    
    public static ArrayList<BaseIngredient> parseBaseIngredientArray(JSONArray objs) {
        ArrayList<BaseIngredient> ingredients = new ArrayList<BaseIngredient>();
        try {
            for (int i=0; i < objs.length(); i++) {
                JSONObject jsonIngredient = objs.getJSONObject(i);
                ingredients.add(parseBaseIngredient(jsonIngredient));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ingredients;
    }
    
    public static ArrayList<Recipe> parseRecipeArray(JSONArray objs) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();
        try {
            for (int i=0; i < objs.length(); i++) {
                JSONObject jsonRecipe = objs.getJSONObject(i);
                recipes.add(parseRecipe(jsonRecipe));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }
    
}