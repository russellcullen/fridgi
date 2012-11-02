package com.fridgi.api;

import com.fridgi.models.Ingredient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Api {
    private static final String BASE_URL = "http://fridgi.herokuapp.com";
    private static final String INGREDIENTS_URL = BASE_URL + "/ingredients";
    private static final String RECIPES_URL = BASE_URL + "/recipes";
    
    public static ArrayList<Ingredient> getIngredients(){
        URL serverAddress;
        try {
            serverAddress = new URL(INGREDIENTS_URL);
      
            HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
              stringBuilder.append(line + "\n");
            }
            reader.close();
            JSONArray ingredientsJSON = new JSONArray(stringBuilder.toString());
            ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
            for (int i=0; i < ingredientsJSON.length(); i++) {
                JSONObject jsonIngredient = ingredientsJSON.getJSONObject(i);
                Ingredient in = new Ingredient();
                in.setName(jsonIngredient.getString("name"));
                in.setQuantity(jsonIngredient.getDouble("quantity"));
                ingredients.add(in);
            }
            return ingredients;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
