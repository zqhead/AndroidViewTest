package com.csii.androidviewtest.TestAndPractice.XferModeAndShader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试PorterDuffXfermode的使用
 * Created by zqhead on 2018/4/2.
 */

public class TestPorterDuffOne extends View {
    private PorterDuffXfermode mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
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
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(mXfermode);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.saveLayer(new RectF(0, 0, getWidth(), getHeight()), mPaint);
        canvas.restore();
        super.onDraw(canvas);
    }
}
