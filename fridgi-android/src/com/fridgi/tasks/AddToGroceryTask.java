package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;

public class AddToGroceryTask extends AsyncTask<Void, Void, Void> {
    
    private String mFridge;
    private String mIngredient;
    private double mQuantity;
    
    public AddToGroceryTask(String fridge, String ingredient, double quantity) {
        mFridge = fridge;
        mIngredient = ingredient;
        mQuantity = quantity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Api.addIngredientToGroceryList(mFridge, mIngredient, mQuantity);
        return null;
    }
    
    @Override
    protected void onPostExecute(Void none) {
    }      
}