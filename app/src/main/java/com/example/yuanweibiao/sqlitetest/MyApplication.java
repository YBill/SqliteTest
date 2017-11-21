package com.example.yuanweibiao.sqlitetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by yuanweibiao on 2017/11/21.
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
