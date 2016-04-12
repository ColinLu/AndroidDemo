package com.colin.demo.app;

import com.colin.library.base.BaseApplication;

/**
 * Created by Administrator on 2016/3/9.
 */
public class DemoApplication extends BaseApplication {
    private static DemoApplication instance = new DemoApplication();

    public static DemoApplication getInstance() {
        return instance;
    }

    public DemoApplication() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void exit() {
        super.exit();
    }


}
