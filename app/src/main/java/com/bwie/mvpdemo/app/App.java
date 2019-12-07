package com.bwie.mvpdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * @auther: 王亚奇
 * @Date: 2019/12/06
 * @Time:下午 03:07
 * @Description:${DESCRIPTION}
 */
public class App extends Application {
    private static  Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
