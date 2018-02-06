package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试pathMeasure的复杂方法 getPosTan 和 getMatrix
 * Created by zq on 2018/2/4.
 */

public class TestPathMeasureTwo extends View {
    private int mWidth, mHeight;

    private float[] pos;
    private float[] tan;
    private Path path;

    PathMeasure pathMeasure;

    private float circleLength;
    private Paint mPaint;

    private float currentLength;

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
        RectF rectF = new RectF(-200, -200, 200, 200);
        path.addCircle(0,0,200, Path.Direction.CW);

        pathMeasure = new PathMeasure(path,false);
        circleLength = pathMeasure.getLength();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);

        currentLength = 0;
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

        Path line = new Path();
        pathMeasure.getPosTan(currentLength, pos, tan);
        line.moveTo(pos[0], pos[1]);



    }
}
