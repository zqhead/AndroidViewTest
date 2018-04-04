package com.csii.androidviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.Activity.BezierTestWaveOneActivity;
import com.csii.androidviewtest.Activity.BezierTestWaveTwoActivity;
import com.csii.androidviewtest.Activity.CameraTestOneActivity;
import com.csii.androidviewtest.Activity.MatrixTestOneActivity;
import com.csii.androidviewtest.Activity.MatrixTestThreeActivity;
import com.csii.androidviewtest.Activity.MatrixTestTwoActivity;
import com.csii.androidviewtest.Activity.PathMeasureTestActivity;
import com.csii.androidviewtest.Activity.RegionTestActivity;
import com.csii.androidviewtest.Activity.TestGuaGuaKaActivity;
import com.csii.androidviewtest.Activity.TestViewOneActivity;
import com.csii.androidviewtest.Activity.TouchEventTestActivity;
import com.csii.androidviewtest.Activity.ViewGroupTestOneActivity;
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
    private Button startMatrix3;
    private Button startCamera1;
    private Button startTouch1;
    private Button startRegion1;
    private Button startViewGroup1;
    private Button startViewOne;
    private Button startGuaGuaKa;

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
        startMatrix3 = (Button)findViewById(R.id.btn_open_matrix_test_three);
        startMatrix3.setOnClickListener(this);
        startCamera1 = (Button)findViewById(R.id.btn_open_camera_test_one);
        startCamera1.setOnClickListener(this);
        startTouch1 = (Button)findViewById(R.id.btn_touch_test);
        startTouch1.setOnClickListener(this);
        startRegion1 = (Button)findViewById(R.id.btn_start_region_test);
        startRegion1.setOnClickListener(this);
        startViewGroup1 = (Button)findViewById(R.id.btn_open_viewgroup_test);
        startViewGroup1.setOnClickListener(this);
        startViewOne = (Button)findViewById(R.id.start_view_test_one);
        startViewOne.setOnClickListener(this);
        startGuaGuaKa = (Button)findViewById(R.id.start_gua_gua_ka);
        startGuaGuaKa.setOnClickListener(this);



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
            case R.id.btn_open_matrix_test_three:
                startActivity(new Intent(MainActivity.this, MatrixTestThreeActivity.class));
                break;
            case R.id.btn_open_camera_test_one:
                startActivity(new Intent(MainActivity.this, CameraTestOneActivity.class));
                break;
            case R.id.btn_touch_test:
                startActivity(new Intent(MainActivity.this, TouchEventTestActivity.class));
                break;
            case R.id.btn_start_region_test:
                startActivity(new Intent(MainActivity.this, RegionTestActivity.class));
                break;
            case R.id.btn_open_viewgroup_test:
                startActivity(new Intent(MainActivity.this, ViewGroupTestOneActivity.class));
                break;

            case R.id.start_view_test_one:
                startActivity(new Intent(MainActivity.this, TestViewOneActivity.class));
                break;
            case R.id.start_gua_gua_ka:
                startActivity(new Intent(MainActivity.this, TestGuaGuaKaActivity.class));
                break;


            default:
                break;
        }

    }
}
