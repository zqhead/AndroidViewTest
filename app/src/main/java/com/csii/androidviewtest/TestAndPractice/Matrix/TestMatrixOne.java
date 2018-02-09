package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试matrix的使用
 * Created by zqhead on 2018/2/7.
 */

public class TestMatrixOne extends View {
    int mWidth,mHeight;
    Matrix matrix = new Matrix();
    private Paint mPaint;

    private Matrix mMatrix;
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
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);

        mMatrix = new Matrix();
        mMatrix.hashCode();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();

    }
}

