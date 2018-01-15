package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zqhead on 2018/1/15.
 */

public class TestCanvasAndPaint extends View {

    public TestCanvasAndPaint(Context context) {
        super(context);
    }

    public TestCanvasAndPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestCanvasAndPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
