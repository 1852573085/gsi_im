package com.aqiang.day0714_gisim;

import com.alibaba.android.arouter.launcher.ARouter;
import com.aqiang.common.app.BaseApplication;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        if(true){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
