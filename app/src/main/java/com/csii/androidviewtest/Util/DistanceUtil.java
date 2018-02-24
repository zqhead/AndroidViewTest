package com.csii.androidviewtest.Util;

/**
 * Created by zqhead on 2018/2/24.
 */

public class DistanceUtil {
        /**
         * 距离工具 计算各种距离
         * */

        /**
        * 计算两点的距离
        *
        * @return 返回两点距离
        * */
        public static float getDistance(float x1, float y1, float x2, float y2){
            return (float)Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
        }
}
