package com.csii.androidviewtest.TestAndPractice.XferModeAndShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.csii.androidviewtest.R;
import com.csii.androidviewtest.Util.DimensionUtil;

/**
 * Created by zqhead on 2018/4/4.
 */

public class TestGuaGuaKa extends View {
    private Paint mPaint;
    private Bitmap bkgBitmap;
    private Bitmap layerBitmap;

    private int mLastX;
    private int mLastY;
    private Path mPath;
    private int mHeight;
    private int mWidth;
    public TestGuaGuaKa(Context context) {
        this(context, null);
    }

    public TestGuaGuaKa(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestGuaGuaKa(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayerType(LAYER_TYPE_SOFTWARE, null);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(DimensionUtil.dip2px(getContext(), 20));
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        bkgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.linglong);
        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //这里使用另一种方式来创建一个bitmap
        layerBitmap = Bitmap.createBitmap(w, h , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(layerBitmap);
        mHeight = h;
        mWidth = w;
        canvas.drawColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bkgBitmap, 0, 0, mPaint);
        canvas.saveLayer(0, 0, mWidth, mHeight, mPaint);


        //这里使用drawbitmap或者使用drawcolor都可以，但是必须在drawpath后面加上setxFermode（null）
        canvas.drawBitmap(layerBitmap, 0, 0, mPaint);
        //canvas.drawColor(Color.GRAY);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawPath(mPath, mPaint);
        mPaint.setXfermode(null);//这里必须加上这个，不然效果出不来，具体原因还要进一步研究（判断应该是如果不及时将Xfermode置空，会影响重绘时候的效果// ）

        canvas.restore();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                mPath.moveTo(mLastX, mLastY);
                break;
            case MotionEvent.ACTION_MOVE:

                int dx = Math.abs(x - mLastX);
                int dy = Math.abs(y - mLastY);

                if (dx > 3 || dy > 3)
                    mPath.lineTo(x, y);

                mLastX = x;
                mLastY = y;
                break;
        }

        invalidate();
        return true;
    }
}
