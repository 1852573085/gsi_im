package com.aqiang.day0714_gisim.mvp.model.service;

import com.aqiang.day0714_gisim.api.FindApi;
import com.aqiang.day0714_gisim.entity.LocalEntity;
import com.aqiang.day0714_gisim.mvp.contract.LocalContract;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.RetrofitManager;

import io.reactivex.Observable;

public class LocalService implements LocalContract.LocalModel {
    @Override
    public Observable<BaseResponseEntity<Boolean>> uploadLocal(LocalEntity localEntity) {
        return RetrofitManager.getInstance().create(FindApi.class).uploadLocal(localEntity);
    }
}
