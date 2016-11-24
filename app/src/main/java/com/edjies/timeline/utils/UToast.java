package com.edjies.timeline.utils;

import android.app.Activity;
import android.widget.Toast;
/**
 * @author  hubble
 */
public class UToast {


    public static void toastInfo(Activity context, String text) {
        if(!"".equals(text))
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toastError(Activity context, String text) {
        if(!"".equals(text))
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
