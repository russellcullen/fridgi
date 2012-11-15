package com.fridgi;

import android.app.Activity;
import android.os.Bundle;

import com.fridgi.tasks.FridgeTask;

public class StartActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FridgeTask task = new FridgeTask(this, "fridgi");
        task.execute();
    }
}
