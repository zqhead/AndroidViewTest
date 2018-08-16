package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.OpenGLES.TestGLSurfaceView;
import com.csii.androidviewtest.R;

public class Opengles2TestOneActivity extends AppCompatActivity {

    private TestGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opengles2_test_one);
    }
}
