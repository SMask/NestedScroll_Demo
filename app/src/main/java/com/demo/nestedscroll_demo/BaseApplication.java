package com.demo.nestedscroll_demo;

import android.app.Application;

/**
 * 应用基类Application(继承于框架基类Application)
 * Created by lishilin on 2016/11/29.
 */
public class BaseApplication extends Application {

    public static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }

}
