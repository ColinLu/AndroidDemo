package com.colin.library.help;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Administrator on 2016/3/1.
 */
public class SystemHelp {

    private static volatile SystemHelp instance = null;
    private SystemHelp() {
    }

    public static SystemHelp getInstance() {
        if (null == instance) {
            synchronized (SystemHelp.class) {
                if (null == instance) {
                    instance = new SystemHelp();
                }
            }
        }
        return instance;
    }



    /**
     * 隐藏输入法面板
     */
    public void hideKeyword(Activity activity) {
        InputMethodManager imm = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));
        if (imm != null && activity.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
