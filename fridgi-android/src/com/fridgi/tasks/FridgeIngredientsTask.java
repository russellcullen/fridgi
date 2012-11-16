package com.fridgi.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.fridgi.adapters.IngredientAdapter;
import com.fridgi.api.Api;
import com.fridgi.models.Fridge;
import com.fridgi.util.Globals;

public class FridgeIngredientsTask extends AsyncTask<Void, Void, Fridge> {
    
    private ListView mList;
    private Context mContext;
    
    public FridgeIngredientsTask(Context context, ListView list) {
        mContext = context;
        mList = list;
    }

    @Override
    protected Fridge doInBackground(Void... params) {
        Fridge fridge = Api.getFridge("fridgi");
        Globals.getInstance().setFridge(fridge);
        return fridge;
    }
    
    @Override
    protected void onPostExecute(Fridge result) {
        IngredientAdapter adapter = new IngredientAdapter(mContext, result.getIngredients(), IngredientAdapter.LARGE_LIST);
        mList.setAdapter(adapter);
    }      
}