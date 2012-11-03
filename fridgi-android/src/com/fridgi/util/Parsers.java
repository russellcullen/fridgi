package com.fridgi.util;

import com.fridgi.models.BaseIngredient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Parsers {
    
    public static BaseIngredient parseBaseIngredient(JSONObject obj) {
        BaseIngredient ingredient = new BaseIngredient();
        try {
            ingredient.setCalories(obj.getInt("calories"));
            ingredient.setName(obj.getString("name"));
            ingredient.setPrice(obj.getDouble("price"));
            ingredient.setQuantity(obj.getDouble("quantity"));
            ingredient.setShelfLife(obj.getLong("shelf_life"));
//            ingredient.setTags((String[]) obj.get("default_tags")); Deal with this later
            ingredient.setUnit(obj.getString("unit"));
            ingredient.setUpc(obj.getLong("upc"));
            return ingredient;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
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
    
}