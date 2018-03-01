package com.csii.androidviewtest.TestAndPractice.Matrix;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.csii.androidviewtest.R;

/**
 * 测试camera的旋转 rotate方法
 * Created by zqhead on 2018/2/27.
 */

public class TestMatrixCameraTwo extends View {
    private Matrix mMatrix;
    private Camera mCamera;
    private Bitmap mBitmap;

    private float degree;

    private int mViewWidth;
    private int mViewHeight;

    private ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0.0f, 360.0f);
    public TestMatrixCameraTwo(Context context) {
        super(context);
        init();
    }

    public TestMatrixCameraTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestMatrixCameraTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mCamera = new Camera();
        mMatrix = new Matrix();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.linglong);

        degree = 0;
        mValueAnimator.setDuration(3000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float angle = (Float)animation.getAnimatedValue();
                degree = angle;
                invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        mCamera.rotateZ(degree);
        mCamera.getMatrix(mMatrix);

        mMatrix.preTranslate(-mViewWidth / 2, -mViewHeight / 2);
        mMatrix.postTranslate(mViewWidth / 2, mViewHeight / 2);

        canvas.setMatrix(mMatrix);

        canvas.drawBitmap(mBitmap, -mBitmap.getWidth() / 2, -mBitmap.getHeight() / 2, new Paint());
    }

    public void doAnimator(){
        mValueAnimator.start();
    }

}
