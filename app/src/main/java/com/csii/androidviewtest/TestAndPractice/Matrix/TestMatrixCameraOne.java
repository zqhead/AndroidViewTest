package com.csii.androidviewtest.TestAndPractice.Matrix;

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

import com.csii.androidviewtest.R;

/**
 * camera与matrix联动测试
 * Created by zqhead on 2018/2/24.
 */

public class TestMatrixCameraOne extends View {
    private Matrix mMatrix;
    private Camera mCamera;
    private Bitmap mBitmap;
    public TestMatrixCameraOne(Context context) {
        super(context);
        init();
    }

    public TestMatrixCameraOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestMatrixCameraOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mCamera = new Camera();
        mMatrix = new Matrix();
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.linglong);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();

        //mMatrix.preTranslate(100, 100);
        mCamera.translate(100, 100, 100);//x向右100， y向上100 z向屏幕内100

        mCamera.applyToCanvas(canvas);
        canvas.drawBitmap(mBitmap, mMatrix, new Paint());
    }
}
