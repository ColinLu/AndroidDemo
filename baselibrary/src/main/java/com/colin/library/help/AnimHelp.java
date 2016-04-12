package com.colin.library.help;

import android.app.Activity;

import com.colin.library.base.R;

/**
 * Created by Administrator on 2016/3/1.
 */
public class AnimHelp {
    private static AnimHelp ourInstance = new AnimHelp();

    public static AnimHelp getInstance() {
        return ourInstance;
    }

    private AnimHelp() {
    }

    /**
     * 显示动画
     */
    public void showOpenAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    /**
     * 显示动画
     */
    public void showCloseAnim(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
