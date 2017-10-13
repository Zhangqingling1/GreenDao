package com.aqinga.greendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/10/1311:21
 */

public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        GreenDaoManger.getInstance();
    }
    public static Context getContext(){
        return context;
    }
}
