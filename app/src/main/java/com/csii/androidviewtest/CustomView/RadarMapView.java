package com.csii.androidviewtest.CustomView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserCompat;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * 雷达图
 * Created by zqhead on 2018/1/23.
 */

public class RadarMapView extends View {
    /**
     * 各项数据名称
     * */
    private String[] texts = {"a", "b", "c","d", "e", "f", "g"};
    /**
     * 雷达图数据组
     * */
    private int[] values = {100 ,30 ,50 ,90 ,20 ,60, 50};
    /**
     * 数据最大值
     * */
    private int maxValue = 100;
    /**
     * 雷达图维度（即有多少个数据项）
     * */
    private int mDimension = 7;
     /**
      * 雷达图刻度（即内部套多少个多边形）
      * */
    private int mScale = 4;
    /*
     * 维度之间的角度（弧度制）
     * */
    private float mAngle = (float)(Math.PI * 2 / mDimension);
    /*
     * 雷达图的半径
     * */
    private float radius;
    /**
     * 雷达图中心点X坐标
     * */
    private float centerX;
    /**
     * 雷达图中心点Y坐标
     * */
    private float centerY;
    /**
     * 雷达区画笔
     * */
    private Paint radarPaint;
    /**
     * 数据区画笔
     * */
    private Paint valuePaint;
    /**
     * 文字画笔
     * */
    private Paint textPaint;

    public RadarMapView(Context context) {
        this(context, null);
    }

    public RadarMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadarMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    private void initData(){
        radarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        radarPaint.setColor(Color.GRAY);
        radarPaint.setAlpha(128);
        radarPaint.setStyle(Paint.Style.STROKE);
        radarPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 2));

        valuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        valuePaint.setColor(0xFF6518CA);
        //valuePaint.setAlpha(128);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int viewSize = Math.min(width,height);
        setMeasuredDimension(viewSize, viewSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (float)(Math.min(w, h)) / 2.0f * 0.9f;
        centerX = (float) w / 2;
        centerY = (float) h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //操作画布到适当的状态
        canvas.translate(centerX, centerY);
        //canvas.rotate(-90);

        //绘制雷达图
        drawRadar(canvas);
        drawLines(canvas);

        //绘制数据图
        drawValues(canvas);

        //绘制文字
        drawTexts(canvas);
    }

    private void drawRadar(Canvas canvas) {
        Path path = new Path();
        for (int i = 1; i <= mScale ; i++) {
            path.reset();//reset()会清空path已有的直线和曲线，但是会保留fillType的设置
            float cRadius = (radius * i / mScale);
            path.moveTo(cRadius, 0);
            for(int j = 1; j < mDimension; j++) {
                float x = (float)(cRadius * Math.cos(mAngle * j));
                float y = (float)(cRadius * Math.sin(mAngle * j));
                path.lineTo(x, y);
            }
            path.close();
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for(int j = 0; j < mDimension; j++) {
            path.reset();
            float x = (float)(radius * Math.cos(mAngle * j));
            float y = (float)(radius * Math.sin(mAngle * j));
            path.lineTo(x, y);
            canvas.drawPath(path, radarPaint);
        }
    }

    private void drawValues(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < mDimension; i++) {
            float r = ((float) values[i] / (float) maxValue) * radius;
            float x = (float)(r * Math.cos(mAngle * i));
            float y = (float)(r * Math.sin(mAngle * i));
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            canvas.drawCircle(x, y, DimensionUtil.dip2px(getContext(), 2), valuePaint);
        }
        path.close();

//        valuePaint.setStyle(Paint.Style.STROKE);
//        valuePaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 1));
//        canvas.drawPath(path, valuePaint);

        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setAlpha(128);
        canvas.drawPath(path, valuePaint);

    }

    private void drawTexts(Canvas canvas) {
        for (int i = 0; i < mDimension; i++) {
            float r = radius;
            float x = (float)(r * Math.cos(mAngle * i));
            float y = (float)(r * Math.sin(mAngle * i));
            canvas.drawText(texts[i], x, y, valuePaint);
        }
    }
}
