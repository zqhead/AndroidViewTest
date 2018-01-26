package com.csii.androidviewtest.TestAndPractice.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 贝塞尔曲线做升降波浪 动态随机晃动
 * Created by zqhead on 2018/1/26.
 */

public class TestBezierWaveTwo extends View{
    public TestBezierWaveTwo(Context context) {
        super(context);
    }

    public TestBezierWaveTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestBezierWaveTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initData(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
