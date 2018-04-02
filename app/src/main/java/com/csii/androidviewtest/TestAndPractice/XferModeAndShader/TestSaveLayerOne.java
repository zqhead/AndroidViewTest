package com.csii.androidviewtest.TestAndPractice.XferModeAndShader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.logging.MemoryHandler;

/**
 * Created by zqhead on 2018/4/2.
 */

public class TestSaveLayerOne extends View {
    private Paint mPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mHeight;
    private int mWidth;
    public TestSaveLayerOne(Context context) {
        this(context, null);
    }

    public TestSaveLayerOne(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestSaveLayerOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //setLayerType(LAYER_TYPE_SOFTWARE, null);
        canvas.drawColor(0x8F00000F);
        canvas.drawRect(0, 0, 400, 400, mPaint);
        //canvas.save(Canvas.MATRIX_SAVE_FLAG);
        int layer = canvas.saveLayer(0, 0 , mWidth, mHeight, mPaint, Canvas.FULL_COLOR_LAYER_SAVE_FLAG);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(300, 300, 400, 400, mPaint);
    }
}
