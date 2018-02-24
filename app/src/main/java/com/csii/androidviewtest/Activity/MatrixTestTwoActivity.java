package com.csii.androidviewtest.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.csii.androidviewtest.BaseActivity;
import com.csii.androidviewtest.R;
import com.csii.androidviewtest.TestAndPractice.Matrix.TestMatrixTwo;

public class MatrixTestTwoActivity extends BaseActivity{

    private RadioGroup radioGroup;//当有多个radioButton时需要使用RadioGroup进行连接
    private TestMatrixTwo testMatrixTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_test_two);

        testMatrixTwo = (TestMatrixTwo) findViewById(R.id.View_matrix_test);


        radioGroup = (RadioGroup) findViewById(R.id.group_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.btn_radio0: testMatrixTwo.setPointCounts(0);break;//零个点相当图reset
                    case R.id.btn_radio1: testMatrixTwo.setPointCounts(1);break;//一个点相当图translate
                    case R.id.btn_radio2: testMatrixTwo.setPointCounts(2);break;//两个点支持
                    case R.id.btn_radio3: testMatrixTwo.setPointCounts(3);break;
                    case R.id.btn_radio4: testMatrixTwo.setPointCounts(4);break;
                    default:break;

                }
            }
        });
    }
}
