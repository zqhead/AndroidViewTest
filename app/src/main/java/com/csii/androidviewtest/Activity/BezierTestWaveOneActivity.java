package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.bezier.TestBezierWave;

public class BezierTestWaveOneActivity extends BaseActivity {
    TestBezierWave wave1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_test_wave_one);
        wave1 = (TestBezierWave) findViewById(R.id.bezier_wave1);
        wave1.startAnimator();
    }
}
