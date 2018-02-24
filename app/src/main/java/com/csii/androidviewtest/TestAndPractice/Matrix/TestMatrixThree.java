package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.csii.androidviewtest.R;

/**
 * 测试Matrix的其他方法发
 * Created by zqhead on 2018/2/24.
 */

public class TestMatrixThree extends View {
    private static final String TAG = "TestMatrixThree";
    private Bitmap run;
    private Bitmap baby;
    private Bitmap hug;

    private Matrix mMatrix;
    private int mViewWidth;
    private int mViewHeight;
    private RectF viewRect;
    private RectF runRect;
    private RectF babyRect;
    private RectF hugRect;



    private enum imageSelection{
        RUN,
        BABY,
        HUG;
    }

    /**
     * java中直接用大括号的说明
     * 括号里的是初始化块，这里面的代码在创建java对象时执行，而且在构造器之前执行！其实初始化块就是
     * 构造器的补充，初始化快是不能接收任何参数的，定义的一些所有对象共有的属性、方法等内容时就可以
     * 用初始化块了初始化！！好处是可以提高初始化块的复用，提高整个应用的可维护性。
     * */
    {

    }
    public TestMatrixThree(Context context) {
        this(context, null);
    }

    public TestMatrixThree(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestMatrixThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        run = BitmapFactory.decodeResource(getResources(), R.drawable.runman);
        baby = BitmapFactory.decodeResource(getResources(), R.drawable.baby);
        hug = BitmapFactory.decodeResource(getResources(), R.drawable.linglong);
        runRect = new RectF(0, 0, run.getWidth(), run.getHeight());
        babyRect = new RectF(0, 0, baby.getWidth(), baby.getHeight());
        hugRect = new RectF(0, 0, hug.getWidth(), hug.getHeight());

        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        viewRect = new RectF(0, 0, mViewWidth, mViewHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {//后期做一个根据上下手势让三个图片变换位置，并且中间的一张在最前方
        super.onDraw(canvas);
        //canvas.scale(0.5f, 0.5f);

        mMatrix.reset();
        mMatrix.setRectToRect(hugRect, viewRect, Matrix.ScaleToFit.END);
        canvas.drawBitmap(hug, mMatrix, null);

        mMatrix.reset();
        mMatrix.setRectToRect(babyRect, viewRect, Matrix.ScaleToFit.CENTER);
        canvas.drawBitmap(baby, mMatrix, null);

        mMatrix.reset();
        mMatrix.setRectToRect(runRect, viewRect, Matrix.ScaleToFit.START);
        canvas.drawBitmap(run, mMatrix, null);
    }
}
