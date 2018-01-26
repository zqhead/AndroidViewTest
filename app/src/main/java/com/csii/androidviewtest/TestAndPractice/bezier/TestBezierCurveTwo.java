package com.csii.androidviewtest.TestAndPractice.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *用三阶贝塞尔曲线画圆
 * Created by zqhead on 2018/1/24.
 */

public class TestBezierCurveTwo extends View {

    private final float cof = (float)(Math.tan(Math.PI / 8) * 4 / 3);//0.551915024494f;

    private PointF[] pointFs = new PointF[4];
    private PointF[] controlP = new PointF[8];

    private int radius = 200;

    private int mw;
    private int mh;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TestBezierCurveTwo(Context context) {
        this(context,null);
    }

    public TestBezierCurveTwo(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBezierCurveTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void initData(){
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);

        pointFs[0] = new PointF(radius, 0);
        pointFs[1] = new PointF(0, radius);
        pointFs[2] = new PointF(-radius, 0);
        pointFs[3] = new PointF(0, -radius);

        controlP[0] = new PointF(radius, radius*cof);
        controlP[1] = new PointF(radius*cof, radius);
        controlP[2] = new PointF(-radius*cof, radius);
        controlP[3] = new PointF(-radius, radius*cof);
        controlP[4] = new PointF(-radius,-radius*cof);
        controlP[5] = new PointF(-radius*cof, -radius);
        controlP[6] = new PointF(radius*cof, -radius);
        controlP[7] = new PointF(radius, -radius*cof);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();

        canvas.translate(getWidth()/2, getHeight()/2);
        canvas.scale(1, -1);

        path.moveTo(pointFs[0].x, pointFs[0].y);
        path.cubicTo(controlP[0].x, controlP[0].y, controlP[1].x, controlP[1].y, pointFs[1].x, pointFs[1].y);
        path.cubicTo(controlP[2].x, controlP[2].y, controlP[3].x, controlP[3].y, pointFs[2].x, pointFs[2].y);
        path.cubicTo(controlP[4].x, controlP[4].y, controlP[5].x, controlP[5].y, pointFs[3].x, pointFs[3].y);
        path.cubicTo(controlP[6].x, controlP[6].y, controlP[7].x, controlP[7].y, pointFs[0].x, pointFs[0].y);

        canvas.drawPath(path, mPaint);

    }
}
