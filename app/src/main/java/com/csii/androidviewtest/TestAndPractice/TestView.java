package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * Created by zqhead on 2018/1/15.
 */

public class TestView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint loopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int viewSize = Math.min(width, height);
        setMeasuredDimension(viewSize, viewSize);
    }

    public void initData() {
        loopPaint.setStyle(Paint.Style.STROKE);
        loopPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 3));
        loopPaint.setARGB(128, 177, 178, 179);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawCircle(getWidth()/ 2, getHeight() / 2, getWidth() / 2, loopPaint);

        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(0xFF9500);
        mPaint.setAlpha(255);
        canvas.drawArc(rectF, 0, 120, true, mPaint);
        mPaint.setColor(0x18F45E);
        mPaint.setAlpha(255);
        canvas.drawArc(rectF, 120, 120, true, mPaint);
        mPaint.setColor(0x00FFC4);
        mPaint.setAlpha(255);
        canvas.drawArc(rectF, 240, 84, true, mPaint);

        //如果在setColor时使用的是RGB的颜色，则alpha会被默认设置为00
        mPaint.setColor(0x00AA33);
        mPaint.setAlpha(255);
        canvas.drawArc(rectF, 324, 36, true, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(255);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2 , getWidth() / 4, mPaint);


    }
}
