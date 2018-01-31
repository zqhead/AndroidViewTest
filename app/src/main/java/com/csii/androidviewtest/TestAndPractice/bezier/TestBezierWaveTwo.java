package com.csii.androidviewtest.TestAndPractice.bezier;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Interpolator;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * 贝塞尔曲线做升降波浪 动态随机晃动（动态随机晃动还未实现）
 * Created by zqhead on 2018/1/26.
 */

public class TestBezierWaveTwo extends View{
    /**
     * 最大水位高度
     * */
    private int maxHeight;
    /**
     * 最小水位高度
     * */
    private int startHeight;
    /**
     * 控件高度
     * */
    private int mHeight;
    /**
     * 控件宽度
     * */
    private int mWidth;
    /**
     * 波峰高度
     * */
    private int peakHeight;
    /**
     * 当前水位高度
     * */
    private int currentHeight;
    /**
     * 当前波峰的位置
     */
    private int currentWidth;
    /**
     * 持续时间
     * */
    private int duration;
    private PointF controlP, endP;
    private int leftWidth, rightWidth;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestBezierWaveTwo(Context context) {
        this(context, null);
    }

    public TestBezierWaveTwo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBezierWaveTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){
        mPaint.setColor(0xFF05DBE6);
        mPaint.setStyle(Paint.Style.FILL);
        duration = 10000;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
        startHeight = mHeight* 7/ 8;
        maxHeight = mHeight / 8;
        peakHeight = mHeight / 16;
        currentHeight = startHeight;
        leftWidth  = 0;
        rightWidth = mWidth;
        currentWidth = leftWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0, currentHeight);
        path.quadTo(currentWidth, currentHeight - peakHeight, mWidth, currentHeight);//二阶贝塞尔曲线来对水位的波峰进行构建
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    public void doAnimator() {
        //用动画来控制y轴 纵向的水位高度变化
        ValueAnimator valueAnimator = ValueAnimator.ofInt(startHeight, maxHeight);
        valueAnimator.setDuration(duration);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int height = (int) animation.getAnimatedValue();
                currentHeight = height;
                invalidate();
            }
        });
        valueAnimator.start();

        //用动画来控制x轴 横向的波峰的移动
        ValueAnimator valueAnimator1 = ValueAnimator.ofInt(leftWidth, rightWidth);
        valueAnimator1.setDuration(4000);
        valueAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int x = (int) animation.getAnimatedValue();
                currentWidth = x;
                invalidate();
            }
        });
        valueAnimator1.start();

    }

    //自定义typeEvaluator
    private class waveEvaluator implements TypeEvaluator{
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            PointF start = (PointF) startValue;
            PointF end = (PointF) endValue;

            int x = (int) (start.x + fraction * (end.x - start.x));
            int y = (int) (start.y + fraction * (end.y - start.y));

            return new PointF(x, y);

        }
    }

    //自定义interpolator
    class myInterpolator extends Interpolator{
        public myInterpolator(int valueCount) {
            super(valueCount);
        }
    }


}
