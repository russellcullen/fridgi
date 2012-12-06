package com.fridgi.util;

import com.fridgi.models.FridgeIngredient;
import com.fridgi.models.Ingredient;
import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;

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
    
    public static String titleCase(String str) {
        StringBuilder b = new StringBuilder(str);
        int i = 0;
        do {
          b.replace(i, i + 1, b.substring(i,i + 1).toUpperCase());
          i =  b.indexOf(" ", i) + 1;
        } while (i > 0 && i < b.length());
        return b.toString();
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
    
    public static void refreshFridge(FridgeCallback callback) {
        FridgeTask fridge = new FridgeTask(Globals.getInstance().getFridge().getName(), callback);
        fridge.execute();
    }

}
