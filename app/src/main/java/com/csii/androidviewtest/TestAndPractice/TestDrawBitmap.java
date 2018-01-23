package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.csii.androidviewtest.R;

/**
 * 测试drawBitmap的基本基本使用
 * Created by zqhead on 2018/1/22.
 */

public class TestDrawBitmap extends View {
    Bitmap bitmap;
    public TestDrawBitmap(Context context) {
        this(context, null);
    }

    public TestDrawBitmap(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestDrawBitmap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
         bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.jaingdaoli);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, new Matrix(), new Paint());
    }
}
