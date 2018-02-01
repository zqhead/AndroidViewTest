package com.csii.androidviewtest.TestAndPractice;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.csii.androidviewtest.Util.DimensionUtil;

import static android.content.ContentValues.TAG;

/**
 * 探究PathMeasure的使用
 * Created by zqhead on 2018/2/1.
 */

public class TestPathMeasure extends View {
    private Paint mPaint;
    private int mWidth, mHeight;
    private float scopeLength;
    private float currentScopeLength;
    private float circleLength;
    private float circleOldLength;
    private float circleNewLength;
    ValueAnimator valueAnimator;
    ValueAnimator valueAnimator1;
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
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 8));

        currentScopeLength = 0;
        scopeLength = 0;
        circleOldLength = 0;
        circleOldLength = 0;
        circleLength = 0;
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

        Path path = new Path();
        RectF rectF = new RectF(-50, -50, 50, 50);
        path.addArc(rectF, 45, 359);

        path.lineTo(100f* (float)Math.cos(Math.PI / 4) , 100 * (float)Math.sin(Math.PI / 4));

        PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, false);
        scopeLength = pathMeasure.getLength();
        Path scope = new Path();

        pathMeasure.getSegment(currentScopeLength, scopeLength, scope, true);
        Log.i(TAG, "onDraw: " +currentScopeLength +"," + scopeLength );
        canvas.drawPath(scope, mPaint);

        Path path1 = new Path();
        RectF oval = new RectF(-100, -100, 100, 100);
        path1.addArc(oval, 45, -359);

        PathMeasure pathMeasure1 = new PathMeasure();
        pathMeasure1.setPath(path1, false);
        circleLength = pathMeasure1.getLength();
        Path circle = new Path();

        pathMeasure1.getSegment(circleOldLength - 10*(circleNewLength - circleOldLength), circleNewLength, circle, true);
        Log.i(TAG, "onDraw: " +circleOldLength +"," + circleNewLength  +","+ circleLength);
        canvas.drawPath(circle, mPaint);
        circleOldLength = circleNewLength;
    }

    public void doAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, scopeLength);
        valueAnimator1 = ValueAnimator.ofFloat(0, circleLength);

        valueAnimator.setRepeatCount(2);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float length = (Float)animation.getAnimatedValue();
                currentScopeLength = length;
                invalidate();
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                valueAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                valueAnimator.pause();
                valueAnimator1.start();
            }
        });
        valueAnimator.start();


//        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
//        valueAnimator1.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator1.setDuration(2000);
        valueAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float length = (Float)animation.getAnimatedValue();
                circleNewLength = length;
                //if(circleOldLength > circleNewLength) circleOldLength = circleNewLength;
                invalidate();

            }
        });

        valueAnimator1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                valueAnimator.resume();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        //valueAnimator1.start();
    }
}
