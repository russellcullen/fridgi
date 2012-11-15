package com.fridgi.tasks;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.fridgi.MainActivity;
import com.fridgi.api.Api;
import com.fridgi.models.Fridge;
import com.fridgi.util.Globals;

public class FridgeTask extends AsyncTask<Void, Void, Fridge> {
    
    private String mFridge;
    private Activity mActivity;
    
    public FridgeTask(Activity activity, String fridge) {
        mFridge = fridge;
        mActivity = activity;
    }

    @Override
    protected Fridge doInBackground(Void... params) {
        return Api.getFridge(mFridge);
    }
    
    @Override
    protected void onPostExecute(Fridge result) {
        Globals.getInstance().setFridge(result);
        Intent i = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(i);
        mActivity.finish();
    }      
}