package com.aqiang.common.router;

import com.alibaba.android.arouter.launcher.ARouter;

public class RouterManager {
    public static void router(String path){
        ARouter.getInstance().build(path).navigation();
    }
}
