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
        //mMatrix = canvas.getMatrix();//使用此种方式获得的matrix，获取的矩阵非单位矩阵，而是带有View相对于屏幕左上角的偏移量 例如Matrix{[1.0, 0.0, 0.0][0.0, 1.0, 240.0][0.0, 0.0, 1.0]}
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, linePaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, linePaint);

        canvas.save();
        Log.i(TAG, "onDraw: " + canvas.getMatrix().toString() );

        int[] loc = new int[2];
        this.getLocationOnScreen(loc);//使用此函数可以获取view左上角在屏幕上的绝对位置坐标
        Log.i(TAG, "onDraw: " + loc[0] + " " + loc[1]);
        mMatrix.preTranslate(mWidth /2,  mHeight / 2);

        //canvas.setMatrix(mMatrix);//setMatrix方法是用Matrix代替canvas现有的matrix失效
        canvas.drawBitmap(bitmap, mMatrix, mPaint);

        //mMatrix.postTranslate(100, 100);
        //canvas.setMatrix(mMatrix);
        //canvas.drawBitmap(bitmap, mMatrix, mPaint);
        canvas.restore();//canvas的save和restore不会影响matrix的操作

        canvas.save();
        //mMatrix.postTranslate(mWidth / 4, mHeight / 4);
        //mMatrix.postRotate(100);//此方法不设置旋转中心，则旋转以原点为旋转中心
        //mMatrix.postRotate(180, mWidth / 2, mHeight / 2);//此方法以后两个数值代表的点做旋转中心，进行旋转（此处原有matrix不是单位矩阵，所以使用前乘和后乘得到得到效果并不同）
        mMatrix.postSkew(0.5f, 0, mWidth / 2, mHeight / 2);//此错切表示水平错切，即y坐标不懂，所有X坐标相对于目标点的差值0.5倍y坐标差值的水平错切，y轴正常
//        mMatrix.setTranslate(0,0);
//        mMatrix.setTranslate(100,100);//使用set会废弃现有matrix，而是使用初始化时矩阵执行set的操作

        Log.i(TAG, "onDraw: " + mMatrix.toString());
        canvas.drawBitmap(bitmap, mMatrix, mPaint);

        canvas.restore();
        canvas.translate(mWidth / 4,  mHeight / 4);

        /**
         * 探究 下面的一段实际执行顺序为 2pre 1set 3post (还未验证，觉得可能是错的)
         * 2018- 2-26日验证，前面的结论错误，下面执行顺序就是按照 1,2,3的顺序执行，并且图片没有回到其实位置（因为缩放改变了图片的大小，移动会原始位置的距离也发生变化，不再是原始图片尺寸的距离
         * 实际执行就是按照整体矩阵的相乘顺序，变换几行代码的位置，其实就是使用矩阵结合律使运算顺序发生变化，但结果不变
         * 但注意，如果中间使用set则前面的pre和post都将失效
         * http://blog.csdn.net/rav009/article/details/7763223
         * */
        mMatrix.reset();
        Log.i(TAG, "onDraw: " + mMatrix.toString());

        mMatrix.setScale(0.4f,0.4f);//1

        //mMatrix.preRotate(180);//1
//        mMatrix.postSkew(0.5f,0);
        Log.i(TAG, "onDraw: " + mMatrix.toString());
        mMatrix.preTranslate(bitmap.getWidth() /2,  bitmap.getHeight() / 2);//2
        Log.i(TAG, "onDraw: " + mMatrix.toString());

        mMatrix.postTranslate(-bitmap.getWidth() /2,  -bitmap.getHeight() / 2);//3
        Log.i(TAG, "onDraw: " + mMatrix.toString());
        canvas.drawBitmap(bitmap, mMatrix, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, event.getX() +","+ event.getY()+ ";" + event.getRawX()+","+ event.getRawY());
        return super.onTouchEvent(event);
    }
}

