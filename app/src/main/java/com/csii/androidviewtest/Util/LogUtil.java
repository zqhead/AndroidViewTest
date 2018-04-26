package com.csii.androidviewtest.Util;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by zqhead on 2018/2/26.
 */

public class LogUtil {
    /**
     * Log工具类 用于调试时使用，根据lEVEl等级打印需要日志，方便使用
     *
     * */
    private final static String TAG = "androidViewTest";
    private final static int VERBOSE = 1;
    private final static int DEBUG = 2;
    private final static int INFO = 3;
    private final static int WARN = 4;
    private final static int ERROR = 5;
    private final static int ASSERT = 6;
    private final static int NOTHING = 7;

    private static int LEVEL = VERBOSE;

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        }
    }

    public static void m(String msg){
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        Log.v(TAG,methodName+":    "+msg);
    }

    public static void m(int msg){
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        Log.v(TAG,methodName+":    "+msg+"");
    }

    public static void m(){
        String methodName = new Exception().getStackTrace()[1].getMethodName();
        Log.v(TAG,methodName);
    }

    public static void v(int msg) {
        LogUtil.v(msg + "");
    }

    public static void d(int msg) {
        LogUtil.d(msg + "");
    }

    public static void i(int msg) {
        LogUtil.i(msg + "");
    }

    public static void w(int msg) {
        LogUtil.w(msg + "");
    }

    public static void e(int msg) {
        LogUtil.e(msg + "");
    }

}
