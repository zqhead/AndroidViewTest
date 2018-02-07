package com.csii.androidviewtest.TestAndPractice;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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
import android.view.animation.LinearInterpolator;

import static android.content.ContentValues.TAG;

/**
 * 测试pathMeasure的复杂方法 getPosTan 和 getMatrix
 * Created by zq on 2018/2/4.
 */

public class TestPathMeasureTwo extends View {
    private int mWidth, mHeight;

    private float[] pos = new float[2];
    private float[] tan = new float[2];
    private Path path;

    PathMeasure pathMeasure;

    private Paint mPaint;

    private float currentLength;

    ValueAnimator valueAnimator;

    public TestPathMeasureTwo(Context context) {
        super(context);
        init();
    }

    public TestPathMeasureTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestPathMeasureTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        path = new Path();
        //RectF rectF = new RectF(-200, -200, 200, 200);
        path.addCircle(0, 0, 200, Path.Direction.CW);

        pathMeasure = new PathMeasure(path, false);
        float circleLength = pathMeasure.getLength();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        currentLength = 0;

        valueAnimator = ValueAnimator.ofFloat(0, circleLength).setDuration(3000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentLength = (Float)animation.getAnimatedValue();
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
        super.onDraw(canvas);
        canvas.translate(mWidth/ 2 , mHeight/ 2);
        canvas.drawPath(path, mPaint);

        @SuppressLint("DrawAllocation") Path line = new Path();
        pathMeasure.getPosTan(currentLength, pos, tan);
        //Log.i(TAG, "onDraw: "+ pos[0] +","+ pos[1] +"," + tan[0] +"," + tan[1]);
        line.moveTo(pos[0], pos[1]);
        line.lineTo(pos[0] + 200 * tan[0], pos[1] + 200 * tan[1]);
        canvas.drawPath(line, mPaint);
    }

    public void doAnimator(){
       valueAnimator.start();

    }


}
