package com.csii.androidviewtest.TestAndPractice.TouchEvent;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * Created by zqhead on 2018/2/27.
 */

public class TestViewGroupTouchEventOne extends LinearLayout {
    public TestViewGroupTouchEventOne(Context context) {
        super(context);
    }

    public TestViewGroupTouchEventOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroupTouchEventOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * ViewGroup的onLayout的方法是个抽象方法（abstract）,所以需要具体实现才可以
     * */
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtil.i("TestViewGroupTouchEventOne", "dispatchTouchEvent" );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtil.i("TestViewGroupTouchEventOne", "onInterceptTouchEvent" );
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.i("TestViewGroupTouchEventOne", "onTouchEvent" );
        return super.onTouchEvent(event);
    }
}
