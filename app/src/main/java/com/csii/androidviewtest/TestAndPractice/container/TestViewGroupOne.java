package com.csii.androidviewtest.TestAndPractice.container;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * 做一个类似于linearLayout的纵向ViewGroup，以此了解ViewGroup的实际操作
 * Created by zqhead on 2018/3/16.
 */

public class TestViewGroupOne extends ViewGroup {

    public TestViewGroupOne(Context context) {
        super(context);
    }

    public TestViewGroupOne(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroupOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        int maxWidth = 0;
        LogUtil.i(getChildCount());
        for(int i = 0; i < getChildCount(); i ++){
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            height += child.getMeasuredHeight();
            maxWidth = maxWidth > child.getMeasuredWidth() ? maxWidth : child.getMeasuredWidth();
        }
        //让最后viewGroup的高度为所有child的高度和 宽度为最长Child的宽度（warp_content模式时）
        //其他模式都为measureSpec的里面的值
        setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //效果是让所有的子View进行纵向排列，首尾相连
        int startTop = t;
        for(int i = 0; i < getChildCount(); i ++){
            View child = getChildAt(i);
            LogUtil.i("TestViewGroupOne - onLayout", l + ", " + t + ", " + r + ", " + b);
            LogUtil.i("TestViewGroupOne - onLayout", child.getLeft() + ", " + child.getTop() + ", " + child.getRight() + ", " + child.getBottom());
            child.layout(l, startTop, l + child.getMeasuredWidth(), startTop + child.getMeasuredWidth());
            startTop = child.getBottom();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }


}
