package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.R;

/**
 * Created by zqhead on 2018/1/15.
 */

public class TestCanvasAndPaint extends View {
    private static final String TAG = "TestCanvasAndPaint";
    Paint normalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint pressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaint;
    int mCounter;

    public TestCanvasAndPaint(Context context) {
        super(context);
    }

    public TestCanvasAndPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    private void initData () {
        Log.i(TAG, "initData: ");
        normalPaint.setColor(Color.BLUE);
        pressPaint.setAlpha(32);
        pressPaint.setColor(getResources().getColor(R.color.presscolor));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(30);
        mPaint = normalPaint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.i(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        super.onDraw(canvas);
        canvas.drawRoundRect(0, 0 , getWidth(), getHeight(), getWidth() / 10, getHeight() / 10, mPaint);

        canvas.drawText("" + mCounter, 100, 100, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPaint = pressPaint;
                postInvalidate();
                mCounter++;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onTouchEvent: " + event.getAction());
                mPaint = normalPaint;
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public int getCounter(){
        return mCounter;
    }

    public void setCounter(int sum) {
        mCounter = sum;
    }
}
