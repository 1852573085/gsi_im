package com.aqiang.day0714_gisim.mvp.model;

import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.day0714_gisim.mvp.contract.MsgContract;
import com.aqiang.day0714_gisim.mvp.model.service.MsgService;
import com.aqiang.net.BaseResponseEntity;

import java.util.List;

import io.reactivex.Observable;

public class MsgRepository extends MsgContract.MsgRepository {
    @Override
    public Observable<BaseResponseEntity<Boolean>> addMsg(MsgEntity msgEntity) {
        return mModel.addMsg(msgEntity);
    }

    @Override
    public Observable<BaseResponseEntity<List<MsgEntity>>> getMsg(String user1, String user2) {
        return mModel.getMsg(user1, user2);
    }

    @Override
    protected void createModel() {
        mModel = new MsgService();
    }
}
