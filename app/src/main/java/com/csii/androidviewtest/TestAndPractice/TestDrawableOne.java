package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.R;

/**
 * 根据这两周开发自定义键盘的实际情况
 * 来具体尝试下Drawable的使用
 * Created by zqhead on 2018/4/26.
 */

public class TestDrawableOne extends View {
    private Paint mPaint;
    private int mViewWidth;
    private int mViewHeight;
    private Drawable mDrawable;
    private Context mContext;
    public TestDrawableOne(Context context) {
        super(context);
        init(context);
    }

    public TestDrawableOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestDrawableOne(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        //通过此方式，在不加载图片的情况下获取图片实际宽高尺寸，单位是像素
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(getResources(), R.drawable.linglong, options);
        int imageHeight = options.outHeight;
        int imageWidth  = options.outWidth;

        //获取一个drawable实例
        mDrawable = mContext.getResources().getDrawable(R.drawable.linglong);
        //设置drawable的边界
        mDrawable.setBounds(0, 0, imageWidth, imageHeight);
        //将图片绘制到canvas上
        mDrawable.draw(canvas);

    }
}
