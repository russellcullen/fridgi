package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;

public class RemoveFromGroceryTask extends AsyncTask<Void, Void, Void> {
    
    private String mFridge;
    private String mIngredient;
    
    public RemoveFromGroceryTask(String fridge, String ingredient) {
        mFridge = fridge;
        mIngredient = ingredient;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Api.removeIngredientFromGroceryList(mFridge, mIngredient);
        return null;
    }
    
    @Override
    protected void onPostExecute(Void none) {
    }      
}