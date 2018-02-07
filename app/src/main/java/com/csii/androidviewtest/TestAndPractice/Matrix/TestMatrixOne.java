package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.content.Context;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试matrix的使用
 * Created by zqhead on 2018/2/7.
 */

public class TestMatrixOne extends View {
    Matrix matrix = new Matrix();
    public TestMatrixOne(Context context) {
        this(context, null);
    }

    public TestMatrixOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestMatrixOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initData(){

    }
}

