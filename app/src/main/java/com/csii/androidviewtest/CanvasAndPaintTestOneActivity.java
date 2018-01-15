package com.csii.androidviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.csii.androidviewtest.TestAndPractice.TestCanvasAndPaint;

public class CanvasAndPaintTestOneActivity extends AppCompatActivity {
    private static final String TAG = "CanvasAndPaintTestOneAc";
    public TestCanvasAndPaint test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_and_paint_test_one);
        test1 = (TestCanvasAndPaint) findViewById(R.id.test_1);

    }
}
