package com.csii.androidviewtest.TestAndPractice.container;

import android.content.Context;
import android.graphics.Canvas;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * 探究scroller的使用 模仿完成简单的ViewPager
 * Created by zqhead on 2018/3/19.
 */

public class TestScrollerOne extends ViewGroup {
    private Scroller mScroller;

    private float mXDown;
    private float mXMove;
    private float theLastXMove;
    private float minTouchX;
    private int index;

    private int leftScreen;
    private int rightScreen;
    public TestScrollerOne(Context context) {
        this(context, null);
    }

    public TestScrollerOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        mScroller = new Scroller(this.getContext());

        minTouchX = 100;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0; i < getChildCount(); i++ ) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
       setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
//        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec),
//                resolveSize(height, heightMeasureSpec));
        //this.scrollTo(getMeasuredWidth() * index, 0 );
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int left = l;
            for (int i = 0; i < getChildCount(); i++ ) {
                View child = getChildAt(i);
                LogUtil.i("TestScrollerOne - onLayout", l + ", " + t + ", " + r + ", " + b);
                LogUtil.i("TestScrollerOne - onLayout", child.getLeft() + ", " + child.getTop() + ", " + child.getRight() + ", " + child.getBottom());
                //child.layout(left, t, left + getMeasuredWidth(), t + getMeasuredHeight());
                //这里传给子view的ltrb有坑，上面view的onlayout的ltrb的位置是相对于它父view的位置
                //但是本VIewGroup调用子view的layout时传入的ltrb应该是相对于本VIewGroup内部坐标系的位置，而非viewGroup的父view的位置
                // 这里的子view的参考坐标系为以此viewGroup的左上角（0,0）的坐标系
                child.layout(i * child.getMeasuredWidth(), 0, (i + 1) * child.getMeasuredWidth(), 0 + child.getMeasuredHeight());

                LogUtil.i("TestScrollerOne - onLayout", child.getLeft() + ", " + child.getTop() + ", " + child.getRight() + ", " + child.getBottom());
                left = child.getRight();
            }

            leftScreen = l;
            rightScreen = left;

        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                theLastXMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float current = Math.abs(mXMove - mXDown);
                theLastXMove = mXMove;
                if(current >  minTouchX) {
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int)(theLastXMove - mXMove);
                this.scrollBy(scrolledX, 0);
                theLastXMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
//                index = (getScrollX() + getWidth() / 2) / getWidth();
//                int scrollX = getScrollX() < leftScreen ? leftScreen : getScrollX() > rightScreen ? rightScreen : getScrollX();
//                int endX = index * getWidth() - getScrollX() ;
//
//                mScroller.startScroll((int)theLastXMove, 0 , endX, 0);

                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
