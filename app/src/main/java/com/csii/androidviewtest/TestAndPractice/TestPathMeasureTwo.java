package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试pathMeasure的复杂方法 getPosTan 和 getMatrix
 * Created by zq on 2018/2/4.
 */

public class TestPathMeasureTwo extends View {
    private float[] pos;
    private float[] tan;

    public TestPathMeasureTwo(Context context) {
        super(context);
        init();
    }

    public TestPathMeasureTwo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestPathMeasureTwo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

    }
}
