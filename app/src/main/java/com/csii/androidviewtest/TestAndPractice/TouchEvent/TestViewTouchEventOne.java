package com.csii.androidviewtest.TestAndPractice.TouchEvent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * Created by zqhead on 2018/2/27.
 */

public class TestViewTouchEventOne extends View {
    public TestViewTouchEventOne(Context context) {
        super(context);
    }

    public TestViewTouchEventOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewTouchEventOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventOne", "dispatchTouchEvent" );
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewTouchEventOne", "onTouchEvent" );
        super.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
