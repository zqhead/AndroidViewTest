package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * Created by zqhead on 2018/1/24.
 */

public class TestBezierCurve extends View {
    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Paint linePaint;
    private PointF startP, endP, controlP;
    private Path gesturePath = new Path();

    private float startX;
    private float startY;


    public TestBezierCurve(Context context) {
        this(context, null);
    }

    public TestBezierCurve(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestBezierCurve(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAlpha(128);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 2));

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.GRAY);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAlpha(128);
        linePaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 2));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;

        startP = new PointF(mWidth / 4, mHeight / 2);
        controlP = new PointF(mWidth / 2, mHeight / 2 - mWidth / 4);
        endP = new PointF(mWidth* 3 / 4, mHeight / 2);
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
//        canvas.translate(mWidth / 2, mHeight / 2);
//        canvas.scale(1, -1);

        canvas.drawLines(new float[]{
                        startP.x, startP.y, controlP.x, controlP.y,
                        controlP.x, controlP.y, endP.x, endP.y,},
                linePaint);

        path.moveTo(startP.x, startP.y);
        path.quadTo(controlP.x, controlP.y, endP.x, endP.y);//二阶贝塞尔曲线 默认path当前的位置为初始点 然后设置控制点 终止点

        path.moveTo(200, 0);
        //三阶贝塞尔曲线画四分之一正圆弧，控制点分别为距离轴tan（π/8）* 4/3 * r
        path.cubicTo(200, (float)(200 * Math.tan((Math.PI / 8) * 4 / 3)),//控制点1
                (float)(200 * Math.tan((Math.PI / 8) * 4 / 3)), 200,//控制点2
                0, 200);//终止点

        canvas.drawPath(path, mPaint);
        canvas.drawPath(gesturePath, linePaint);//用二阶贝塞尔曲线实现圆滑手势书写
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
//                controlP.x = event.getX();
//                controlP.y = event.getY();
                float endX = event.getX();
                float endY = event.getY();
                gesturePath.quadTo(startX, startY, (startX + endX)/2, (startY + endY)/2);
                startX = endX;
                startY = endY;
                postInvalidate();
                break;
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                gesturePath.moveTo(startX, startY);
                postInvalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
