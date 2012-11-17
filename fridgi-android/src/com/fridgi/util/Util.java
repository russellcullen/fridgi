package com.fridgi.util;

import com.fridgi.models.FridgeIngredient;
import com.fridgi.models.Ingredient;

import java.util.List;

public class Util {
    
    public static String formatIngredientQuantity(Ingredient ingredient, String unit) {
        StringBuilder sb = new StringBuilder();
        sb.append(ingredient.getQuantity());
        sb.append(" " + unit);
        if (ingredient.getQuantity() != 1) {
            sb.append("s");
        }
        sb.append(" of ");
        return sb.toString();
    }
    
    public static boolean hasIngredient(Ingredient ingredient) {
        if (ingredient == null) return false;
        List<FridgeIngredient> ingredients = Globals.getInstance().getFridge().getIngredients();
        for (FridgeIngredient i : ingredients) {
            if (i.getName().equals(ingredient.getName()) && (i.getQuantity() >= ingredient.getQuantity())) {
                return true;
            }
        }
        return false;
    }

}
