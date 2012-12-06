package com.fridgi.tasks;

import android.os.AsyncTask;

import com.fridgi.api.Api;
import com.fridgi.models.Fridge;
import com.fridgi.util.Globals;

public class FridgeTask extends AsyncTask<Void, Void, Fridge> {
    
    private String mFridge;
    
    public interface FridgeCallback {
        public void onPostExecute();
    }
    
    private FridgeCallback mCallback;
    
    public FridgeTask(String fridge, FridgeCallback callback) {
        mFridge = fridge;
        mCallback = callback;
    }

    @Override
    protected Fridge doInBackground(Void... params) {
        return Api.getFridge(mFridge);
    }
    
    @Override
    protected void onPostExecute(Fridge result) {
        Globals.getInstance().setFridge(result);
        if (mCallback != null) {
            mCallback.onPostExecute();
        }
    }      
}