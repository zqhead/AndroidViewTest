package com.csii.androidviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.Activity.BezierTestWaveOneActivity;
import com.csii.androidviewtest.Activity.BezierTestWaveTwoActivity;
import com.csii.androidviewtest.Activity.MatrixTestOneActivity;
import com.csii.androidviewtest.Activity.MatrixTestTwoActivity;
import com.csii.androidviewtest.Activity.PathMeasureTestActivity;
import com.csii.androidviewtest.TestAndPractice.TestPathMeasure;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button start1;
    private Button start2;
    private Button startPath1;
    private Button startPathMeasure;
    private Button startbezier1;
    private Button startbezier2;
    private Button startbezier3;
    private Button startMatrix1;
    private Button startMatrix2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start1 = (Button) findViewById(R.id.btn_start);
        start1.setOnClickListener(this);
        start2 = (Button)findViewById(R.id.btn_start_two);
        start2.setOnClickListener(this);
        startPath1 = (Button)findViewById(R.id.btn_start_path1);
        startPath1.setOnClickListener(this);
        startbezier1 = (Button)findViewById(R.id.btn_start_bezier1);
        startbezier1.setOnClickListener(this);
        startbezier2 = (Button)findViewById(R.id.btn_open_bezier2);
        startbezier2.setOnClickListener(this);
        startbezier3 = (Button)findViewById(R.id.btn_start_wave2);
        startbezier3.setOnClickListener(this);
        startPathMeasure = (Button)findViewById(R.id.btn_open_test_pathmeasure);
        startPathMeasure.setOnClickListener(this);
        startMatrix1 = (Button)findViewById(R.id.btn_open_matrix_test_one);
        startMatrix1.setOnClickListener(this);
        startMatrix2 = (Button)findViewById(R.id.btn_open_matrix_test_two);
        startMatrix2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startActivity(new Intent(MainActivity.this, CanvasAndPaintTestOneActivity.class));
                break;
            case  R.id.btn_start_two:
                startActivity(new Intent(MainActivity.this, CanvasAndPaintTestTwoActivity.class));
                break;
            case R.id.btn_start_path1:
                startActivity(new Intent(MainActivity.this, PathTestOneActivity.class));
                break;
            case R.id.btn_start_bezier1:
                startActivity(new Intent(MainActivity.this, BezierTestActivity.class));
                break;
            case R.id.btn_open_bezier2:
                startActivity(new Intent(MainActivity.this, BezierTestWaveOneActivity.class));
                break;
            case R.id.btn_start_wave2:
                startActivity(new Intent(MainActivity.this, BezierTestWaveTwoActivity.class));
                break;
            case R.id.btn_open_test_pathmeasure:
                startActivity(new Intent(MainActivity.this, PathMeasureTestActivity.class));
                break;
            case R.id.btn_open_matrix_test_one:
                startActivity(new Intent(MainActivity.this, MatrixTestOneActivity.class));
                break;
            case R.id.btn_open_matrix_test_two:
                startActivity(new Intent(MainActivity.this, MatrixTestTwoActivity.class));
                break;

            default:
                break;
        }

    }
}
