package com.edjies.timeline.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @author  hubble
 */
public class UScreen {

    /**
     * 获取屏幕宽度 DP
     *
     * @param activity
     * @return
     */
    public static int getScreenWidthDp(Activity activity) {

        DisplayMetrics  dm = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels; // 屏幕宽（dip，如：320dip）

    }

    /**
     * 获取屏幕高度 DP
     * @param activity
     * @return
     */
    public static int getScreenHeightDp(Activity activity) {

        DisplayMetrics  dm = new DisplayMetrics();

        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;// 屏幕宽（dip，如：533dip）

    }

    public static int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
