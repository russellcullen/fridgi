package com.fridgi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.api.Api;
import com.fridgi.models.Fridge;

public class FridgeIngredientsTask extends AsyncTask<Void, Void, Fridge> {
    
    private ListView mList;
    private Context mContext;
    
    public FridgeIngredientsTask(Context context, ListView list) {
        mContext = context;
        mList = list;
    }

    @Override
    protected Fridge doInBackground(Void... params) {
        return Api.getFridge("fridgi");
    }
    
    @Override
    protected void onPostExecute(Fridge result) {
        IngredientAdapter adapter = new IngredientAdapter(mContext, result.getIngredients());
        mList.setAdapter(adapter);
    }      
}