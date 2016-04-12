package com.colin.library.base;

import android.app.Activity;
import android.app.Application;

import com.colin.library.help.ImageLoaderHelp;
import com.colin.library.help.LogHelp;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局变量，整个App运行的时候都会存在
 * 实现ImageLoader值得初始化
 *
 * @author Colin
 */
public class BaseApplication extends Application {

    public BaseApplication() {
        super();
    }

    protected List<Activity> activityList = new ArrayList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化图片加载类
        ImageLoaderHelp.initImageLoader(getApplicationContext());
    }

    public void exit() {
        // 关闭数据库,数据库账户信息推出状态
        try {
            for (Activity activity : activityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void addActivity(Activity activity) {
        activityList.add(activity);
        LogHelp.d("addActivity:" + activity.getClass().getSimpleName());
    }

}
