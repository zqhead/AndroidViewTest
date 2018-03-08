package com.csii.androidviewtest.TestAndPractice.TouchEvent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * Created by zqhead on 2018/3/8.
 */

public class TestViewTouchEventTwo extends View {

    public TestViewTouchEventTwo(Context context) {
        super(context);
    }

    public TestViewTouchEventTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewTouchEventTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);

        canvas.drawColor(Color.YELLOW);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventTwo", "dispatchTouchEvent" );
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventTwo", "onTouchEvent" );
        LogUtil.i("TestViewTouchEventTwo", "X:"+event.getX()+ ", RawX:" + event.getRawX());
        LogUtil.i("TestViewTouchEventTwo", "Y:"+event.getX()+ ", RawY:" + event.getRawX());
        super.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
