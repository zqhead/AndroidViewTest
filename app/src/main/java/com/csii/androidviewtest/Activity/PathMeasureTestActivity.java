package com.csii.androidviewtest.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testPathMeasure.completeAnimator();
            }
        }, 5000);

        Button start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPathMeasure.doAnimator();
            }
        });

        Button complete = (Button) findViewById(R.id.btn_complete);
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testPathMeasure.completeAnimator();
            }
        });
    }
}
