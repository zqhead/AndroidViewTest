package com.csii.androidviewtest.TestAndPractice.XferModeAndShader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.logging.MemoryHandler;

/**
 * 重写imageVIew 构建圆角矩形的图片控件
 * Created by zqhead on 2018/4/4.
 */

public class RCImageView extends ImageView {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    public RCImageView(Context context) {
        this(context, null);
    }

    public RCImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RCImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        //
        canvas.saveLayer(0, 0, mWidth, mHeight, mPaint, Canvas.ALL_SAVE_FLAG);

        //这里暂时有问题，还是不能从imageView获取它本身的图片资源
//        Drawable drawable = getDrawable();
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//        canvas.drawBitmap(bitmap, 0, 0, mPaint);
//        super.draw(canvas);


        Bitmap src = createSrcBmp();
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(src, 0, 0, mPaint);
        //canvas.drawCircle(mWidth / 2, mHeight / 2, Math.min(mWidth, mHeight) / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restore();
    }

    private Bitmap createSrcBmp() {
        Bitmap bmp = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        canvas.drawCircle(mWidth / 2, mHeight / 2, Math.min(mWidth, mHeight) / 2, mPaint);
        return bmp;
    }
}
