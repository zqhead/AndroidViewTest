package com.csii.androidviewtest.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.TestPathMeasure;

public class PathMeasureTestActivity extends BaseActivity {
    TestPathMeasure testPathMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_measure_test);
        testPathMeasure = (TestPathMeasure)findViewById(R.id.view_test_pathmeasure);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testPathMeasure.doAnimator();
            }
        }, 1000);
    }
}
