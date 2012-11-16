package com.fridgi.util;

import com.fridgi.models.FridgeIngredient;
import com.fridgi.models.Ingredient;

import java.util.List;

public class Util {
    
    public static boolean hasIngredient(Ingredient ingredient) {
        if (ingredient == null) return false;
        List<FridgeIngredient> ingredients = Globals.getInstance().getFridge().getIngredients();
        for (FridgeIngredient i : ingredients) {
            if (i.getName().equals(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }

}
