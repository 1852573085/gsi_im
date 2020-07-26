package com.aqiang.day0714_gisim.mvp.model;

import com.aqiang.day0714_gisim.entity.LocalEntity;
import com.aqiang.day0714_gisim.mvp.contract.LocalContract;
import com.aqiang.day0714_gisim.mvp.model.service.LocalService;
import com.aqiang.net.BaseResponseEntity;

import io.reactivex.Observable;

public class LocalRepository extends LocalContract.LocalRepository {
    @Override
    public Observable<BaseResponseEntity<Boolean>> uploadLocal(LocalEntity localEntity) {
        return mModel.uploadLocal(localEntity);
    }

    @Override
    protected void createModel() {
        mModel = new LocalService();
    }
}
