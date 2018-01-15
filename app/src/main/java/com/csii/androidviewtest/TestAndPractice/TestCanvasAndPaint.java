package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zqhead on 2018/1/15.
 */

public class TestCanvasAndPaint extends View {
    Paint mPaint = new Paint();
    int mCounter;

    public TestCanvasAndPaint(Context context) {
        super(context);
    }

    public TestCanvasAndPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData () {
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(0, 0 , getWidth(), getHeight(), getWidth() / 10, getHeight() / 10, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawText(""+mCounter, 100, 100, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint.setColor(Color.DKGRAY);
                mCounter++;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                mPaint.setColor(Color.BLUE);
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
