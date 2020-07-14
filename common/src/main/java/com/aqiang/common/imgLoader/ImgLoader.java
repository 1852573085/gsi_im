package com.aqiang.common.imgLoader;

import android.content.Context;

import com.aqiang.common.imgLoader.setting.ImgSetting;

public class ImgLoader {
    private ImgLoad imgLoad;
    private ImgLoader(){
        imgLoad = new GlideLoader();
    }
    private static ImgLoader imageLoad = new ImgLoader();

    public static ImgLoader getInstance(){
        return imageLoad;
    }

    public void setImgLoad(ImgLoad imgLoad){
        this.imgLoad = imgLoad;
    }

    public void imgLoad(Context context, ImgSetting setting){
        imgLoad.imgLoad(context,setting);
    }
}
