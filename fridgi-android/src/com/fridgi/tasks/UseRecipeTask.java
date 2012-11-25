package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;

public class UseRecipeTask extends AsyncTask<Void, Void, Void> {
    
    private String mFridge;
    private String mRecipeId;
    
    public UseRecipeTask(String fridge, String recipeId) {
        mFridge = fridge;
        mRecipeId = recipeId;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Api.useRecipe(mFridge, mRecipeId);
        return null;
    }
    
    @Override
    protected void onPostExecute(Void result) {
    }      
}