package com.csii.androidviewtest.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.Matrix.TestMatrixCameraTwo;

public class CameraTestOneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_test_one);

        final TestMatrixCameraTwo view1 = (TestMatrixCameraTwo) findViewById(R.id.view_camera_two);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view1.doAnimator();
            }
        },2000);
    }
}
