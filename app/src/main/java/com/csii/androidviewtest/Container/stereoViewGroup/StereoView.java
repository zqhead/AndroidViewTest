package com.csii.androidviewtest.Container.stereoViewGroup;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by zqhead on 2018/3/20.
 */

public class StereoView extends ViewGroup {
    private int index;

    private Camera mCamera;
    private Matrix mMatrix;
    private Scroller mScroller;

    private int minTouchSlop;

    private float mXDown;
    private float mYDown;

    private VelocityTracker mVelocityTracker;

    public StereoView(Context context) {
        this(context, null);
    }

    public StereoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        minTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();// ViewConfiguration.get(context).getScaledTouchSlop() 用于获取滑动 它获得的是触发移动事件的最短距离
        mMatrix = new Matrix();
        mCamera = new Camera();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        scrollTo(0, index * getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int Top = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.layout(0, Top, getMeasuredWidth(), Top + child.getMeasuredHeight());
                Top += child.getHeight();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                mXDown = ev.getX();
                mYDown = ev.getY();
                break;
            case MotionEvent.ACTION_DOWN:
                float mXMove = ev.getX();
                float mYMove = ev.getY();
                return isCanScrolling(mXMove, mYMove);
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isCanScrolling(float mXMove, float mYMove) {
        if (Math.abs(mYMove - mYDown) > minTouchSlop && Math.abs(mYMove - mYDown) > Math.abs(mXMove - mXDown)) {
            return true;
        } else return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
