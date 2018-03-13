package com.csii.androidviewtest.TestAndPractice;

import android.content.Context;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试Path和Region的联动
 * Created by zqhead on 2018/3/13.
 */

public class TestPathRegion extends View {
    Region mRegion;
    public TestPathRegion(Context context) {
        super(context);
    }

    public TestPathRegion(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestPathRegion(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
