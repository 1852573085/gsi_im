package com.aqiang.common.imgLoader;

import android.content.Context;

import com.aqiang.common.imgLoader.setting.ImgSetting;

public interface ImgLoad<Setting extends ImgSetting> {
    void imgLoad(Context context,Setting setting);
}
