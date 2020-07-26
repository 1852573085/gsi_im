package com.aqiang.day0714_gisim.mvp.model.service;

import com.aqiang.day0714_gisim.api.FindApi;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.day0714_gisim.mvp.contract.MsgContract;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.RetrofitManager;

import java.util.List;

import io.reactivex.Observable;

public class MsgService implements MsgContract.MsgModel {
    @Override
    public Observable<BaseResponseEntity<Boolean>> addMsg(MsgEntity msgEntity) {
        return RetrofitManager.getInstance().create(FindApi.class).addMsg(msgEntity);
    }

    @Override
    public Observable<BaseResponseEntity<List<MsgEntity>>> getMsg(String user1, String user2) {
        return RetrofitManager.getInstance().create(FindApi.class).getMsg(user1,user2,0,10);
    }
}
