package com.edjies.timeline.utils;

import android.content.Context;

/**
 * app程序信息
 * @author  hubble
 */

public class UApp {
    // 获取程序版本名称
    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        }catch (Exception e) {
            return "";
        }
    }

    // 获取程序版本号
    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }catch (Exception e) {
            return 1;
        }
    }
}
