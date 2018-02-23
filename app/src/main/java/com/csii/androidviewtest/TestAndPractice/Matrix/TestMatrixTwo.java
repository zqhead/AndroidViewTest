package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.csii.androidviewtest.R;

/**
 * matrix的其他用法探究 如setPolyToPoly
 * Created by zqhead on 2018/2/23.
 */

public class TestMatrixTwo extends View {
    private static final String TAG = "TestMatrixTwo";
    public Matrix mMatrix;
    public Bitmap mBitmap;
    public float[] src;
    public float[] dst;


    public TestMatrixTwo(Context context) {
        super(context);
        init();
    }

    public TestMatrixTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestMatrixTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        mMatrix = new Matrix();
        //mMatrix.setPolyToPoly();
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cat);

        float bw = mBitmap.getWidth();
        float bh = mBitmap.getHeight();

        float[] res = {0, 0,
                bw, 0 ,
                bw, bh / 2 ,
                0, bh / 2};
        src = res.clone();
        dst = res.clone();

        dst[0] += 50;
        dst[1] += 50;
        dst[2] -= 50;
        dst[3] += 50;

        mMatrix.setPolyToPoly(src, 0, dst, 0, 4);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //canvas.save();

        //canvas.setMatrix(mMatrix);
        canvas.drawBitmap(mBitmap, mMatrix, new Paint());
    }
}
