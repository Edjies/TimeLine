package com.edjies.timeline;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;



/**
 * activity 基类</br>
 * <ol>
 *     <li>提供处理全局交互的方法</li>
 * </ol>
 * @author hubble
 */
public class BaseActivity extends AppCompatActivity {


    private boolean isResumed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    public boolean isPageResumed() {
        return isResumed;
    }


    /**隐藏输入法*/
    public void hideInputMethod(Context context, IBinder windowToken) {
        // 隐藏输入法
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(windowToken, 0);
        } catch (Exception e) {
        }
    }
}
