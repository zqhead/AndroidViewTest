package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 测试Path和Region的联动
 * Created by zqhead on 2018/3/13.
 */

public class TestPathRegion extends View {
    private Region mRegion, centerR, upR, leftR, bottomR, rightR;
    private Path centerP, upP, leftP, bottomP, rightP;

    private Paint mPaint;
    private int mViewWidth, mViewHeight;
    private Matrix inverseMatrix;

    private final static int NOTHING = 0;
    private final static int LEFT = 1;
    private final static int BOTTOM = 2;
    private final static int RIGHT = 3;
    private final static int TOP = 4;
    private final static int CENTER = 5;

    private int onTouch;

    public TestPathRegion(Context context) {
        this(context, null);
    }

    public TestPathRegion(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestPathRegion(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);

        centerP = new Path();
        upP = new Path();
        leftP = new Path();
        bottomP = new Path();
        rightP = new Path();

        centerR = new Region();
        upR = new Region();
        leftR = new Region();
        bottomR = new Region();
        rightR = new Region();

        onTouch = 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;

        int minWidth = w > h ? h : w;
        minWidth *= 0.8;

        RectF outerRectF = new RectF(-minWidth / 2, -minWidth / 2, minWidth / 2, minWidth / 2);
        RectF innerRectF = new RectF(-minWidth / 4, -minWidth / 4, minWidth / 4, minWidth / 4);

        mRegion = new Region(-w, -h, w, h);

        int outerSweepAngle = 60;
        int innerSweepAngle = -60;

        centerP.addCircle(0, 0, (float) (minWidth * 0.2), Path.Direction.CW);
        centerR.setPath(centerP, mRegion);

        /**
         * Path 弧度复习 两种连接方式
         * addArc（） 直接将arc加入Path中 无视lastPoint的位置
         * ArcTo（） 对比addArc多了一个boolean forceMoveTo ,true 时 和addArc相同（将path的lastPoint移动到圆弧的起始点），false时 会先将lastPoint lineTo到圆弧的起始点 ，当不设置这个参数时，默认为false
         * */
        upP.addArc(outerRectF, 240, outerSweepAngle);
        upP.arcTo(innerRectF, 300, innerSweepAngle);
        upP.close();
        upR.setPath(upP, mRegion);//setPath 可以理解为将path与mRegion的交集设置为upR的区域

        rightP.addArc(outerRectF, 330, outerSweepAngle);
        rightP.arcTo(innerRectF, 30, innerSweepAngle);
        rightP.close();
        rightR.setPath(rightP, mRegion);

        bottomP.addArc(outerRectF, 60, outerSweepAngle);
        bottomP.arcTo(innerRectF, 120, innerSweepAngle);
        bottomP.close();
        bottomR.setPath(bottomP, mRegion);

        leftP.addArc(outerRectF, 150, outerSweepAngle);
        leftP.arcTo(innerRectF, 210, innerSweepAngle);
        leftP.close();
        leftR.setPath(leftP, mRegion);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        if (inverseMatrix == null) {
            inverseMatrix = new Matrix();
            canvas.getMatrix().invert(inverseMatrix);
        }
        mPaint.setColor(Color.GRAY);
        canvas.drawPath(centerP, mPaint);
        canvas.drawPath(upP, mPaint);
        canvas.drawPath(rightP, mPaint);
        canvas.drawPath(bottomP, mPaint);
        canvas.drawPath(leftP, mPaint);

        mPaint.setColor(Color.LTGRAY);
        if (onTouch == LEFT) {
            canvas.drawPath(leftP, mPaint);
        } else if (onTouch == BOTTOM) {
            canvas.drawPath(bottomP, mPaint);
        } else if (onTouch == RIGHT) {
            canvas.drawPath(rightP, mPaint);
        } else if (onTouch == TOP) {
            canvas.drawPath(upP, mPaint);
        } else if (onTouch == CENTER) {
            canvas.drawPath(centerP, mPaint);
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] op = new float[2];
        op[0] = event.getRawX();
        op[1] = event.getRawY();
        //全屏幕的默认矩阵就是一个单位矩阵，通过A*A的逆=E的公式， 让全局点乘以view的canvas的矩阵的逆，就能得到全局点相对于canvas坐标系的坐标了
        inverseMatrix.mapPoints(op);

        int x = (int)op[0];
        int y = (int)op[1];
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                checkContains(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                onTouch = NOTHING;
                invalidate();
                break;
        }

        return true;
    }

    private void checkContains(int x, int y) {
        if (leftR.contains(x, y)){
            onTouch = LEFT;
        } else if (bottomR.contains(x, y)) {
            onTouch = BOTTOM;
        } else if (rightR.contains(x, y)) {
            onTouch = RIGHT;
        } else if (upR.contains(x, y)) {
            onTouch = TOP;
        } else if (centerR.contains(x, y)) {
            onTouch = CENTER;
        } else {
            onTouch = NOTHING;
        }

    }
}
