package com.aqiang.common.log;

import android.os.Build;
import android.util.Log;

import com.aqiang.common.BuildConfig;

public class LogUtils {
    private static final String TAG = "swq";
    private boolean debug = BuildConfig.IsDebug;
    private static LogUtils logUtils = new LogUtils();

    public static LogUtils getInstance(){
        return logUtils;
    }

    public void d(String msg){
        if(debug){
            Log.d(TAG,msg);
        }
    }

    public void i(String msg){
        if(debug){
            Log.i(TAG,msg);
        }
    }

    public void e(String msg){
        if(debug){
            Log.e(TAG,msg);
        }
    }
}
