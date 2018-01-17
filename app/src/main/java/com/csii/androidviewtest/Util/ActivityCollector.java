package com.csii.androidviewtest.Util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zqhead on 2018/1/17.
 */
public class ActivityCollector {
    /**
     * Activity管理工具，用于统计监控以及退出App中运行中的Activity
     * */
    private static List<Activity>  activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAllActivity(){
        for (Activity activity:activityList) {
            if (!activity.isFinishing()) {
               activity.finish();
            }
        }
    }
}
