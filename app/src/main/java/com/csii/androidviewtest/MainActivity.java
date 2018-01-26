package com.csii.androidviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.csii.androidviewtest.Activity.BezierTestWaveOneActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button start1;
    private Button start2;
    private Button startPath1;
    private Button startbezier1;
    private Button startbezier2;

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
        startbezier1 = (Button)findViewById(R.id.btn_open_bezier2);
        startbezier1.setOnClickListener(this);

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
            default:
                break;
        }

    }
}
