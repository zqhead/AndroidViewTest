package com.csii.androidviewtest.Util;

import android.content.Context;

/**
 * Created by zqhead on 2018/1/16.
 */

public class DimensionUtil {
    /**
     * 尺寸工具 用与变换dp与px，以适应不同尺寸屏幕
     * */

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale+0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
