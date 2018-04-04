package com.csii.androidviewtest.TestAndPractice.container;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.FrameMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by zqhead on 2018/4/4.
 */

public class TestRoundRectImageContainer extends FrameLayout {
    private int mHeight;
    private int mWidth;
    private Paint mPaint;

    public TestRoundRectImageContainer(@NonNull Context context) {
        this(context, null);
    }

    public TestRoundRectImageContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestRoundRectImageContainer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        measureChildren(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }


//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        View child = getChildAt(0);
//        child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
//
//    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.drawColor(Color.GRAY);
        canvas.saveLayer(0, 0, mWidth, mHeight, mPaint);
        super.dispatchDraw(canvas);

        Bitmap srcbmp = createSrcBmp();

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(srcbmp, 0, 0, mPaint);
        //canvas.drawCircle(mWidth / 2, mHeight / 2, Math.min(mWidth, mHeight) / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restore();
    }

    private Bitmap createSrcBmp() {
        Bitmap bmp = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawRoundRect(0, 0, mWidth, mHeight, mWidth / 8, mWidth / 8, mPaint);
        return bmp;
    }
}
