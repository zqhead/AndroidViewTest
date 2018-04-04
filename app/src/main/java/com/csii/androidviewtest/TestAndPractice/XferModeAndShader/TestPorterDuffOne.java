package com.csii.androidviewtest.TestAndPractice.XferModeAndShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.Util.LogUtil;

/**
 * 测试PorterDuffXfermode的使用
 * Created by zqhead on 2018/4/2.
 */

public class TestPorterDuffOne extends View {
    private PorterDuff.Mode[] modes = {
            PorterDuff.Mode.CLEAR,//0
            PorterDuff.Mode.SRC,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.SRC_IN,//5
            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.DST_ATOP,//10
            PorterDuff.Mode.XOR,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.MULTIPLY,
            PorterDuff.Mode.SCREEN,//15
            PorterDuff.Mode.ADD,
            PorterDuff.Mode.OVERLAY

    };
    private Bitmap dstBitmap;
    private Bitmap srcBitmap;

    private PorterDuffXfermode mXfermode = new PorterDuffXfermode(modes[5]);
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestPorterDuffOne(Context context) {
        this(context, null);
    }

    public TestPorterDuffOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestPorterDuffOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null); //使用非硬件加速也是一种制造缓冲的方法
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);

        dstBitmap = creatDstBmp();
        srcBitmap = creatSrcBmp();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(0x8000000F);

        //使用saveLayer 来制造隔离缓冲，是的dst和src除目标范围其他都是透明 去除不必要干扰
       // canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, mPaint);
        mPaint.setColor(Color.RED);
        mPaint.setXfermode(mXfermode);
        canvas.drawCircle(300, 300, 100, mPaint);
        mPaint.setXfermode(null);

//        canvas.drawBitmap(dstBitmap, new Matrix(), mPaint);
//        mPaint.setXfermode(mXfermode);
//        canvas.drawBitmap(srcBitmap, new Matrix(), mPaint);
//        mPaint.setXfermode(null);

        canvas.restore();
        LogUtil.i(1);
        //super.onDraw(canvas);
    }

    private Bitmap creatDstBmp() {
        Bitmap bmp = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, mPaint);
        return bmp;
    }

    private Bitmap creatSrcBmp() {
        Bitmap bmp = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(300, 300, 100, mPaint);
        return bmp;
    }
}
