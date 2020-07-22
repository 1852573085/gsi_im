package com.aqiang.common.app;

import android.app.Application;
import android.content.Context;

import com.aqiang.common.SException;

public class BaseApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
//        SException.getInstance().init(this);
    }

    public static Context getContext(){
        return context;
    }
}
