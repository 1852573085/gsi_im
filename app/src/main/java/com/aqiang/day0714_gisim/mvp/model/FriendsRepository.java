package com.aqiang.day0714_gisim.mvp.model;

import com.aqiang.day0714_gisim.mvp.contract.FriendsContract;
import com.aqiang.day0714_gisim.mvp.model.service.FriendsService;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FriendsRepository extends FriendsContract.FriendsRepository {
    @Override
    public Observable<BaseResponseEntity<List<UserEntity>>> getFriends(String usercode) {
        return mModel.getFriends(usercode);
    }

    @Override
    protected void createModel() {
        mModel = new FriendsService();
    }
}
