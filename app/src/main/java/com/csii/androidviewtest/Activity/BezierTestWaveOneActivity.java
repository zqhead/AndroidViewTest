package com.csii.androidviewtest.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.bezier.TestBezierWave;

public class BezierTestWaveOneActivity extends BaseActivity {
    TestBezierWave wave1;

//    Handler mhandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            wave1.doAnimator();
//            super.handleMessage(msg);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier_test_wave_one);
        wave1 = (TestBezierWave) findViewById(R.id.bezier_wave1);
        wave1.startAnimator();
//        mhandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mhandler.sendEmptyMessage(1);
//            }
//        },5000);

    }
}
