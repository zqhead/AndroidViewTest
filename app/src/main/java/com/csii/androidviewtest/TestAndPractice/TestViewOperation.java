package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zq on 2018/1/19.
 */

public class TestViewOperation extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TestViewOperation(Context context) {
        super(context);
    }

    public TestViewOperation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewOperation(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GREEN);
        mPaint.setAlpha(128);
        canvas.translate(200,200);
        RectF rectF = new RectF(0, 0, 200, 200);
        canvas.drawRect(rectF,mPaint);
    }


}
