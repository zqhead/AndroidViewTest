package com.csii.androidviewtest.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.bezier.TestBezierWaveTwo;

public class BezierTestWaveTwoActivity extends BaseActivity {

    private TestBezierWaveTwo wave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_test_wave_two);
        wave = (TestBezierWaveTwo)findViewById(R.id.bezier_wave2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wave.doAnimator();
            }
        },1000);

    }
}
