package com.fridgi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fridgi.adapters.RecipeAdapter;
import com.fridgi.api.Api;
import com.fridgi.models.Recipe;

import java.util.ArrayList;

public class SearchFridgeRecipesTask extends AsyncTask<Void, Void, ArrayList<Recipe>> {
    
    private ListView mList;
    private Context mContext;
    private String mFridge;
    private String mQuery;
    
    public SearchFridgeRecipesTask(String query, String fridge, Context context, ListView list) {
        mQuery = query;
        mFridge = fridge;
        mContext = context;
        mList = list;
    }

    @Override
    protected ArrayList<Recipe> doInBackground(Void... params) {
        return Api.searchFridgeRecipes(mQuery, mFridge);
    }
    
    @Override
    protected void onPostExecute(ArrayList<Recipe> result) {
        RecipeAdapter adapter = new RecipeAdapter(mContext, result);
        mList.setAdapter(adapter);
    }      
}