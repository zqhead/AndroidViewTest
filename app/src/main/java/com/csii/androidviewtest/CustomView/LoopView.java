package com.csii.androidviewtest.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqhead on 2018/1/19.
 */

public class LoopView extends View {
    private static final String TAG = "LoopView";

    private int[] fillColor = new int[]{0xFFFF9500, 0xFF18F45E, 0xFF00FFC4, 0xFFE32636, 0xFF00AA44, 0xFFC805E6};
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<LoopData> dataList = new ArrayList<>();

    private int mWidth;
    private int mHeight;
    private RectF rectF;
    private float startAngle;

    public LoopView(Context context) {
       this(context, null);
    }

    public LoopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){
        mPaint.setStyle(Paint.Style.FILL);
        startAngle = 0.0f;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int viewSize = Math.min(width, height);
        setMeasuredDimension(viewSize, viewSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float radius = mWidth / 2;
        rectF = new RectF(-radius, -radius, radius, radius);
        int index = 0;

        //移动画布
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.rotate(-90);

        //进行填充
        for (LoopData loop: dataList) {
            mPaint.setColor(fillColor[index % fillColor.length]);
            canvas.drawArc(rectF, startAngle, loop.angle, true, mPaint);
            startAngle += loop.angle;
            index++;
        }

        if (dataList.size() == 0) {
            mPaint.setColor(fillColor[0]);
            canvas.drawCircle(0, 0, mWidth / 2, mPaint);
        }

        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, mWidth * 0.3f, mPaint);
    }


    /*
    * 初始化数据
    * */
    public void initLoopData(List<LoopData> list) {
        int total = 0;
        dataList.clear();
        for(LoopData loop:list){
            total += loop.dataValue;
        }
        Log.i(TAG, "initLoopData:total "+total);
        for(LoopData loop:list){
            loop.percentage = (float)loop.dataValue /(float) total;
            loop.angle = loop.percentage * 360.0f;
            dataList.add(loop);
            Log.i("LoopView", "initLoopData: dataList name" + loop.dataName + " " +loop.dataValue + " "+ loop.percentage + " "+ loop.angle);
        }
        Log.i(TAG, "initLoopData:dataList.size "+dataList.size());
        startAngle = 0.0f;
        postInvalidate();
    }

    //内部类用于初始化数据
    public class LoopData{
        public String dataName;
        public int dataValue;
        public float percentage;

        public float angle;

        public LoopData(String name, int value) {
            super();
            dataName = name;
            dataValue = value;
        }
    }
}
