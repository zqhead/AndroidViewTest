package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zqhead on 2018/1/31.
 */

public class TestPathFillType extends View {
    private int mWidth;
    private int mHeight;

    private Paint mPaint;
    private int viewSize;

    public TestPathFillType(Context context) {
        super(context);
        init();
    }

    public TestPathFillType(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestPathFillType(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        viewSize = Math.min(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawColor(Color.GRAY);
        Path path = new Path();
        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();
        Path path5 = new Path();
//        path.setFillType(Path.FillType.EVEN_ODD);//奇偶规则 （奇偶规则就是任意点发出一条射线，与边界焦点个数是奇数则在图形内，偶数则在图形外）
//        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);//反向机构规则
        //用path的布尔操作画一个太极

        canvas.translate(mWidth / 2, mHeight / 2);
        path.addCircle(0, 0, viewSize / 2, Path.Direction.CW);
        path1.addCircle(0, viewSize / 4, viewSize / 4, Path.Direction.CW);
        path2.addCircle(0, -viewSize / 4, viewSize / 4, Path.Direction.CW);
        path3.addRect(-viewSize / 2, viewSize / 2, 0, -viewSize / 2, Path.Direction.CW);
        path4.addCircle(0, viewSize / 4, viewSize / 16, Path.Direction.CW);
        path5.addCircle(0, -viewSize / 4, viewSize / 16, Path.Direction.CW);

        path.op(path3, Path.Op.DIFFERENCE);//取除去path3后差集给path 为右半圆
        path.op(path1, Path.Op.UNION);//取交集
        path.op(path2, Path.Op.DIFFERENCE);//取差集
        path.op(path4, Path.Op.XOR);//取异或
        path.op(path5, Path.Op.UNION);

        canvas.drawPath(path, mPaint);

        //画圈
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        canvas.drawCircle(0, 0, viewSize, mPaint);

    }
}
