package com.csii.androidviewtest.Util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zqhead on 2018/2/26.
 */

public class ToastUtil {

    /**
     * Toast工具类，用于输出Toast信息
     * */

    public static void showInfo(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
