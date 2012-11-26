package com.fridgi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.fridgi.tasks.FridgeTask;
import com.fridgi.tasks.FridgeTask.FridgeCallback;

public class StartActivity extends Activity implements FridgeCallback {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FridgeTask task = new FridgeTask(this);
        task.execute();
    }

    @Override
    public void onPostExecute() {
      Intent i = new Intent(this, MainActivity.class);
      startActivity(i);
      finish();
    }
}
