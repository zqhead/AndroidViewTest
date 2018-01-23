package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * Created by zqhead on 2018/1/23.
 */

public class TestPath extends View {
    public TestPath(Context context) {
        this(context, null);
    }

    public TestPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void initData(){
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        Path path = new Path();
        //用路径画一矩形,顺时针连接
        path.addRect(100, 100, -100, -100, Path.Direction.CW);
        //改变最后一个点的坐标，影响的是矩形第四象限的点(100,-100)
        path.setLastPoint(-100,-200);
        //如果不移动直接连线，起点为第一象限点不是第四象限的点
        path.moveTo(100,-100);//将起始点移动到（100，-100）
        path.lineTo(0,0);
        path.lineTo(200,0);
        path.close();

        path.setLastPoint(300, 0);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 2));

        canvas.drawPath(path, mPaint);

        Path path1 = new Path();
        RectF rectF = new RectF(-200, -200, 200, 200);//四个参数(left,top,right,bottom) 要确保left<=right top <= bottom

        path1.lineTo(0, 200);
        //path1.addArc(rectF,0,180);//直接把圆弧arc加入path中
        path1.arcTo(rectF,45,180);//会先将path最后一次操作的点与arc的起始点进行lineTo，然后在画圆弧,如果没有lastpoint则不会将起始点与原点相连
        canvas.drawPath(path1,mPaint);
    }
}
