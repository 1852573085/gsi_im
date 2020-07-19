package com.aqiang.day0714_gisim;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aqiang.common.app.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if(true){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
