package com.aqiang.day0714_gisim.mvp.model;

import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.day0714_gisim.mvp.model.service.FindService;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FindRepository extends FindContract.FindRepository {
    @Override
    public Observable<BaseResponseEntity<List<UserEntity>>> findFriend(String username, String nick) {
        return mModel.findFriend(username, nick);
    }

    @Override
    protected void createModel() {
        mModel = new FindService();
    }
}
