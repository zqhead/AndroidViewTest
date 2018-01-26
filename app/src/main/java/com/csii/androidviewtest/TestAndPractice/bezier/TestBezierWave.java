package com.csii.androidviewtest.TestAndPractice.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 用二阶贝塞尔曲线实现波浪图
 * Created by zqhead on 2018/1/25.
 */

public class TestBezierWave extends View {
    private int waterHeight;
    private int wavePeakHeight;
    private int waveTroughDepth;
    private int mWidth, mHeight;

    private PointF startP, firstP, secondF, thirdP, endP;
    private PointF[] controlP = new PointF[4];;
    private Paint mPaint;
    private boolean mHasInit = false;
    private boolean mIsRunning = false;

    public TestBezierWave(Context context) {
        super(context);
        init();
    }

    public TestBezierWave(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestBezierWave(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        waterHeight = h / 2;
        wavePeakHeight = - h / 16;
        waveTroughDepth = h / 16;
        reset();
        mHasInit = true;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void reset(){
        startP = new PointF(0, waterHeight);
        firstP = new PointF(mWidth / 2f, waterHeight);
        secondF = new PointF( mWidth, waterHeight);
        thirdP = new PointF(mWidth * 3f / 2f, waterHeight);
        endP = new PointF(mWidth * 2f, waterHeight);
        controlP[0] = new PointF(mWidth * 1/ 4f, waterHeight + wavePeakHeight);
        controlP[1] = new PointF(mWidth * 3/ 4f, waterHeight + waveTroughDepth);
        controlP[2] = new PointF(mWidth * 5/ 4f, waterHeight + wavePeakHeight);
        controlP[3] = new PointF(mWidth * 7/ 4f, waterHeight + waveTroughDepth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        if (!mIsRunning || !mHasInit)
            return;
        path.moveTo(startP.x, startP.y);
        path.quadTo(controlP[0].x, controlP[0].y, firstP.x, firstP.y);
        path.quadTo(controlP[1].x, controlP[1].y, secondF.x, secondF.y);
        path.quadTo(controlP[2].x, controlP[2].y, thirdP.x, thirdP.y);
        path.quadTo(controlP[3].x, controlP[3].y, endP.x, endP.y);

        path.lineTo(endP.x, mHeight);
        path.lineTo(startP.x, mHeight);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                reset();
                doAnimator();
                mIsRunning = true;
            }
            super.handleMessage(msg);

        }
    };

    //波浪动画
    private void doAnimator(){
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.ofFloat(0, -mWidth);//设置Value动画执行的value范围
        valueAnimator.setDuration(1000);//设置持续时间 单位毫秒
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//设置重复次数 范围[0,ValueAnimator.INFINITE] ValueAnimator.INFINITE表示一致重复
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);//设置重复的方式 可选值 ValueAnimator.RESTART（重新执行上一次的动画） ValueAnimator.REVERSE（反向执行前一次的动画）
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {//设置动画执行过程的监听器
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float x = (Float)animation.getAnimatedValue();//getAnimatedValue() 获取当前的value值 返回值类型是Object 与Ofxxx的时候设置的类型相同 比如 ofFloat 则返回值为Float
                Log.e("test", "onAnimationUpdate: " + x);
                startP.x = x;
                firstP.x = startP.x + mWidth / 2f;
                secondF.x = firstP.x + mWidth / 2f;
                thirdP.x = secondF.x + mWidth / 2f;
                endP.x = thirdP.x + mWidth / 2f;
                controlP[0].x = startP.x + mWidth / 4f;
                controlP[1].x = firstP.x + mWidth / 4f;
                controlP[2].x = secondF.x + mWidth / 4f;
                controlP[3].x = thirdP.x + mWidth / 4f;
                postInvalidate();
            }
        });
        valueAnimator.start();//启动动画
    }
    //发送
    public void startAnimator(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!mHasInit) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Message msg = new Message();
                msg.what = 0;
                mHandler.sendMessage(msg);
            }
        }).start();

    }
}

