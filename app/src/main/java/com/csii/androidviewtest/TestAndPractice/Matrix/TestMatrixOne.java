package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.R;

import static android.content.ContentValues.TAG;

/**
 * 测试matrix的使用 主要为基础仿射变换和pre（前乘）及post（后乘）的探索
 * Created by zqhead on 2018/2/7.
 */

public class TestMatrixOne extends View {
    int mWidth,mHeight;
    Matrix matrix = new Matrix();
    private Paint mPaint;

    private Matrix mMatrix;
    private Paint linePaint;
    private Bitmap bitmap;
    public TestMatrixOne(Context context) {
        this(context, null);
    }

    public TestMatrixOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestMatrixOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    public void initData(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);

        mMatrix = new Matrix();//直接生成bitmap对象的方式 画drawBitmap时会让matrix的操作相对于view控件内部坐标，但是用setmatrix则是相对于全屏幕的坐标的操作
                                //另外 使用canvas.getMatrix获得matrix,通过这个matrix画drawbitmap，会将相对于全屏幕的的坐标结果，映射到view内的坐标上

        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.jaingdaoli);
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
        //mMatrix = canvas.getMatrix();//使用此种方式获得的matrix，它的坐标操作是相对于全屏幕的，在view内画drawbitmap时会将bitmap按照全屏幕的运算坐标结果，映射到view内部的坐标系上，所以和预想得到的结果会有偏差
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, linePaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, linePaint);
//
        canvas.save();
        Log.i(TAG, mWidth / 2 +","+ mHeight / 2);
        mMatrix.preTranslate(mWidth /2,  mHeight / 2);

        //canvas.setMatrix(mMatrix);//setMatrix方法是用Matrix代替canvas现有的matrix失效
        canvas.drawBitmap(bitmap, mMatrix, mPaint);

        //mMatrix.postTranslate(100, 100);
        //canvas.setMatrix(mMatrix);
        //canvas.drawBitmap(bitmap, mMatrix, mPaint);
        canvas.restore();//canvas的save和restore不会影响matrix的操作

        //mMatrix.postTranslate(mWidth / 4, mHeight / 4);
        //mMatrix.postRotate(100);//此方法不设置旋转中心，则旋转以原点为旋转中心
        //mMatrix.postRotate(180, mWidth / 2, mHeight / 2);//此方法以后两个数值代表的点做旋转中心，进行旋转（此处原有matrix不是单位矩阵，所以使用前乘和后乘得到得到效果并不同）
        mMatrix.postSkew(0.5f, 0, mWidth / 2, mHeight / 2);//此错切表示水平错切，即y坐标不懂，所有X坐标相对于目标点的差值0.5倍y坐标差值的水平错切，x轴正常向
//        mMatrix.setTranslate(0,0);
//        mMatrix.setTranslate(100,100);//使用set会废弃现有matrix，而是使用初始化时矩阵执行set的操作

        Log.i(TAG, "onDraw: " + mMatrix.toString());
        canvas.drawBitmap(bitmap, mMatrix, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, event.getX() +","+ event.getY()+ ";" + event.getRawX()+","+ event.getRawY());
        return super.onTouchEvent(event);
    }
}

