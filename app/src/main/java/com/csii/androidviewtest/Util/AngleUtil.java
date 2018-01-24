package com.csii.androidviewtest.Util;

/**
 * Created by zqhead on 2018/1/24.
 */

public class AngleUtil {
    /**
     * 角度工具用于进行 角度与弧度转换
     * */
    public static float angle2Arc (float angle) {
        return (float)(angle * Math.PI / 180.0f);
    }

    public static float arc2Angle (float arc) {
        return (float)(arc * 180.0f / Math.PI);
    }
}
