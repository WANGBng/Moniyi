package com.bwie.wang.wangzuoye.application;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * date:2018/12/26.
 * @author 王丙均
 */

public class MyApplication extends Application {
    //绘制页面时参照的设计图宽度
    public final static float DESIGN_WIDTH = 750;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        resetDensity();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetDensity();
    }

    public void resetDensity() {
        Point size = new Point();
        ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        getResources().getDisplayMetrics().xdpi = size.x/DESIGN_WIDTH*72f;
    }
}
