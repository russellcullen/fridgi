package com.fridgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;
import com.fridgi.util.Globals;

public class StartActivity extends Activity implements FridgeCallback {
    
    ProgressDialog mDialog;
    String mFridge;
    EditText mName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Globals.getInstance().getFridge() != null) {
            startMain();
            return;
        }
        mFridge = loadFridge();
        if (mFridge != null) {
            startTask(mFridge);
            return;
        }
        setContentView(R.layout.start_activity);
        
        getActionBar().hide();
        
        mName = (EditText) findViewById(R.id.fridge);
        
        Button go = (Button) findViewById(R.id.start);
        go.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                mFridge = mName.getText().toString();
                
                startTask(mFridge);
            }
        });
        
    }
    
    public void startTask(String name) {
        mDialog = new ProgressDialog(StartActivity.this);
        mDialog.setMessage("Loading Fridge: " + name);
        mDialog.setCancelable(false);
        mDialog.show();
        
        FridgeTask task = new FridgeTask(name, StartActivity.this);
        task.execute();
    }

    @Override
    public void onPostExecute() {
        mDialog.dismiss();
        saveFridge();
        startMain();
    }
    
    public void startMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    
    public String loadFridge() {
        SharedPreferences prefs = getSharedPreferences(
                "com.fridgi", Context.MODE_PRIVATE);
        return prefs.getString("com.fridgi.name", null);
    }
    
    public void saveFridge() {
        SharedPreferences prefs = getSharedPreferences(
                "com.fridgi", Context.MODE_PRIVATE);
        prefs.edit().putString("com.fridgi.name", mFridge).commit();
    }
}
