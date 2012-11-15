package com.fridgi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.api.Api;
import com.fridgi.models.BaseIngredient;

import java.util.ArrayList;

public class IngredientsTask extends AsyncTask<Void, Void, ArrayList<BaseIngredient>> {
    
    private ListView mList;
    private Context mContext;
    
    public IngredientsTask(Context context, ListView list) {
        mContext = context;
        mList = list;
    }

    @Override
    protected ArrayList<BaseIngredient> doInBackground(Void... params) {
        return Api.getIngredients();
    }
    
    @Override
    protected void onPostExecute(ArrayList<BaseIngredient> result) {
        IngredientAdapter adapter = new IngredientAdapter(mContext, result);
        mList.setAdapter(adapter);
    }      
}