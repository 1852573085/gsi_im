package com.aqiang.common.imgLoader;

import android.content.Context;

import com.aqiang.common.imgLoader.setting.CircleSetting;
import com.aqiang.common.imgLoader.setting.ImgSetting;
import com.aqiang.common.imgLoader.setting.NomalSetting;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

public class GlideLoader implements ImgLoad<CircleSetting> {
    @Override
    public void imgLoad(Context context, CircleSetting setting) {
        CircleCrop circleCrop = null;
        if(setting.isCircle()){
            circleCrop = new CircleCrop();
        }
        Glide.with(context)
                .load(setting.urlPath)
                .transform(circleCrop)
                .into(setting.imageView);
    }
}
