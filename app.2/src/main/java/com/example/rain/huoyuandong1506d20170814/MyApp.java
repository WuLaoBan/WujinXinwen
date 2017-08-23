package com.example.rain.huoyuandong1506d20170814;

import android.app.Application;

import org.xutils.x;


/**
 * data 2017/8/14.
 * author:霍远东(Rain)
 * function:
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
