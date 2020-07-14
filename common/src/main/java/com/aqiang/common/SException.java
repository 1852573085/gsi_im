package com.aqiang.common;

import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.widget.Toast;

public class SException implements Thread.UncaughtExceptionHandler {
    private Context context;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
    private SException(){

    }
    private static SException sException = new SException();

    public static SException getInstance(){
        return sException;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!handleException(t,e) && uncaughtExceptionHandler != null){
            uncaughtExceptionHandler.uncaughtException(t,e);
        }else {
            SystemClock.sleep(3000);
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    private boolean handleException(Thread t, Throwable e) {
        if(t == null && e == null){
            return false;
        }
        String message = e.getMessage();
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context,"哎呦！程序挂了",Toast.LENGTH_SHORT).show();
                Looper.loop();

            }
        }.start();
        return true;
    }

    public void init(Context context){
        this.context = context;
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

}
