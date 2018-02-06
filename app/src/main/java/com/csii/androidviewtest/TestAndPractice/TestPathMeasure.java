package com.csii.androidviewtest.TestAndPractice;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.FragmentBreadCrumbs;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.csii.androidviewtest.Util.DimensionUtil;

import static android.content.ContentValues.TAG;

/**
 * 探究PathMeasure的使用
 * https://github.com/GcsSloop/AndroidNote/blob/master/CustomView/Advance/Code/SearchView.java
 * Created by zqhead on 2018/2/1.
 * 2-2做大改调整
 */

public class TestPathMeasure extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private float scopeLength;
    private float currentScopeLength;
    private float searchLength;
    private float circleOldLength;
    private float circleNewLength;

    private boolean isOver;

    private State mCurrentState = State.Normal;

    private static enum State{
        Normal,
        Starting,
        Searching,
        Ending
    }

    //三个阶段的动画类
    private ValueAnimator startAnimator;
    private ValueAnimator searchAnimator;
    private ValueAnimator endAnimator;

    private Path scopePath;
    private Path searchPath;
    /**
     * 两个路径测量类
     */
    private PathMeasure scopePathMeasure;
    private PathMeasure searchPathMeasure;

    private Animator.AnimatorListener mAnimationListener;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (mCurrentState){
                case Starting:
                    isOver = false;
                    mCurrentState = State.Searching;
                    searchAnimator.start();
                    break;
                case Searching:
                    if(!isOver) {
                        circleOldLength = 0;
                        searchAnimator.start();
                    } else {
                        mCurrentState = State.Ending;
                        endAnimator.start();
                    }
                    break;
                case Ending:
                    mCurrentState = State.Normal;
                    break;
                default:
                    break;
            }
            invalidate();
        }
    };


    public TestPathMeasure(Context context) {
        super(context);
        init();
    }

    public TestPathMeasure(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestPathMeasure(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置线帽类型 butt无线帽 round 圆线帽 square方线帽
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 8));

        isOver = true;

        currentScopeLength = 0;
        circleOldLength = 0;
        circleOldLength = 0;

        initPath();
        initListener();
        initAnimator();
    }

    /**
     * 初始化路径
     * */
    private void initPath(){
        //初始化放大镜的路径
        scopePath = new Path();
        RectF rectF = new RectF(-50, -50, 50, 50);
        scopePath.addArc(rectF, 45, 359.9f); // 画弧不能画满圆，否则系统会默认优化，会把360直接优化为0

        scopePath.lineTo(100f * (float)Math.cos(Math.PI / 4),
                100f * (float)Math.sin(Math.PI / 4));

        scopePathMeasure = new PathMeasure(scopePath, false);//构造函数 将一个path绑定在一个PathMeasure上 并且第二个参数的意思是，如果path已闭合则无意义，如果没有闭合 true会自动将path闭合 false则不闭合保持path原状
        scopeLength = scopePathMeasure.getLength();//获取路径的长度

        searchPath = new Path();
        RectF oval = new RectF(-100, -100, 100, 100);
        searchPath.addArc(oval, 45, -359.9f);

        searchPathMeasure = new PathMeasure(searchPath, false);
        searchLength = searchPathMeasure.getLength();
    }

    private void initListener(){
        mAnimationListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };
    }

    private void initAnimator(){
        /**
         * 初始化三个阶段的动画对象
         * */
        startAnimator = ValueAnimator.ofFloat(0, scopeLength).setDuration(2000);
        searchAnimator = ValueAnimator.ofFloat(0, searchLength).setDuration(2000);
        endAnimator = ValueAnimator.ofFloat(scopeLength, 0).setDuration(2000);

        startAnimator.setInterpolator(new LinearInterpolator());
        searchAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        endAnimator.setInterpolator(new LinearInterpolator());

        startAnimator.addListener(mAnimationListener);
        searchAnimator.addListener(mAnimationListener);
        endAnimator.addListener(mAnimationListener);

        startAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float length = (Float)animation.getAnimatedValue();
                currentScopeLength = length;
                invalidate();
            }
        });

        searchAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float length = (Float)animation.getAnimatedValue();
                circleNewLength = length;
                invalidate();
            }
        });

        endAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float length = (Float)animation.getAnimatedValue();
                currentScopeLength = length;
                invalidate();
            }
        });
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawColor(0xFF3DCBFF);

        switch (mCurrentState) {
            case Normal:
                canvas.drawPath(scopePath, mPaint);
                break;
            case Starting:
                scopePathMeasure.setPath(scopePath, false);
                Path scope = new Path();
                scopePathMeasure.getSegment(currentScopeLength, scopeLength, scope, true);
                canvas.drawPath(scope, mPaint);
                break;
            case Searching:
                Path circle = new Path();
                searchPathMeasure.getSegment(circleOldLength - (circleNewLength - circleOldLength) * 3,
                        circleNewLength,
                        circle, true);
                canvas.drawPath(circle, mPaint);
                circleOldLength = circleNewLength;
                break;
            case Ending:
                Path reverseScope = new Path();
                scopePathMeasure.getSegment(currentScopeLength, scopeLength, reverseScope, true);//getSegment的功能 把截取的路径添加到第三参数的目标路径上，而非替换
                                                                                                         //所以如果目标路径上有值，则截取路径会加在原有路径的后面，这是第四个参数startWithMoveTo的含义是
                                                                                                         //是否将原有路径和新加路径连接起来，以保证路径的连贯性
                                                                                                         //true 是 使用moveTo将起始点移动到相应位置 false 不使用moveTo 会将原有路径终止点与新路径的起始点lineTo
                canvas.drawPath(reverseScope, mPaint);
                break;
            default:
                break;

        }
    }

    public void doAnimator() {
        mCurrentState = State.Starting;
        startAnimator.start();
    }

    public void completeAnimator(){
        isOver = true;
    }
}
