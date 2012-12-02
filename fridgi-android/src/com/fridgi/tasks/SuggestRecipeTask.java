package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;
import com.fridgi.models.Recipe;

public class SuggestRecipeTask extends AsyncTask<Void, Void, Recipe> {
    
    public interface SuggestRecipeHandler {
        public void handleRecipe(Recipe recipe);
    }
    
    private String mFridge;
    private String mRecipeId;
    private SuggestRecipeHandler mHandler;
    
    public SuggestRecipeTask(String fridge, String recipeId, SuggestRecipeHandler handler) {
        mFridge = fridge;
        mRecipeId = recipeId;
        mHandler = handler;
    }

    @Override
    protected Recipe doInBackground(Void... params) {
        return Api.suggestRecipe(mFridge, mRecipeId);
    }
    
    @Override
    protected void onPostExecute(Recipe result) {
        mHandler.handleRecipe(result);
    }      
}