package com.csii.androidviewtest;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.Util.ActivityCollector;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }
}
