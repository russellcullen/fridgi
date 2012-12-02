package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;
import com.fridgi.models.Fridge;
import com.fridgi.util.Globals;

public class FridgeTask extends AsyncTask<Void, Void, Fridge> {
    
    private static String FRIDGE = "fridgi";
    
    public interface FridgeCallback {
        public void onPostExecute();
    }
    
    private FridgeCallback mCallback;
    
    public FridgeTask(FridgeCallback callback) {
        mCallback = callback;
    }

    @Override
    protected Fridge doInBackground(Void... params) {
        return Api.getFridge(FRIDGE);
    }
    
    @Override
    protected void onPostExecute(Fridge result) {
        Globals.getInstance().setFridge(result);
        mCallback.onPostExecute();
    }      
}