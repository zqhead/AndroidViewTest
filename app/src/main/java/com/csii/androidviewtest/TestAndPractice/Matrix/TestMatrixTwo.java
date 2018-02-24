package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.app.AliasActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.method.Touch;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.R;
import com.csii.androidviewtest.Util.DistanceUtil;

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
    private float[] res;

    private int pointCounts;
    private int boundsRadius = 100;
    private Paint circlePaint;

    {
        pointCounts = 0;
    }


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

    private void init(){

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.RED);
        circlePaint.setAlpha(127);
        circlePaint.setStyle(Paint.Style.FILL);


        mMatrix = new Matrix();
        //mMatrix.setPolyToPoly();
        mBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.cat);

        float bw = mBitmap.getWidth();
        float bh = mBitmap.getHeight();

        float[] orgin = {0, 0,
                bw, 0 ,
                bw, bh,
                0, bh};
        res = orgin.clone();
        src = res.clone();
        dst = res.clone();

        mMatrix.setPolyToPoly(src, 0, dst, 0, pointCounts);
    }

    private void reset(){
        mMatrix.setPolyToPoly(src, 0, dst, 0, pointCounts);
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
        canvas.translate(100, 100);
        canvas.drawBitmap(mBitmap, mMatrix, new Paint());
        for (int i = 0; i < pointCounts; i++) {
            canvas.drawCircle(dst[2 * i], dst[2 * i + 1], 20, circlePaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float gesX = event.getX();
                float gesY = event.getY();

                for (int i = 0; i < dst.length; i += 2) {
                    float distance = DistanceUtil.getDistance(dst[i] + 100, dst[i + 1] + 100, gesX, gesY);
                    if (distance < boundsRadius) {
                        dst[i] = gesX - 100;
                        dst[i + 1] = gesY - 100;
                        break;
                    }
                }
                reset();
                invalidate();
                break;

            default:
                break;
        }
        return true;
    }

    public void setPointCounts(int counts) {
        int sum = counts< 0 || counts > 4 ? 4 : counts;
        pointCounts = sum;
        src = res.clone();
        dst = res.clone();
        reset();
        invalidate();
    }
}
