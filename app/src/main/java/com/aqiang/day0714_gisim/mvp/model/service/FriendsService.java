package com.aqiang.day0714_gisim.mvp.model.service;

import com.aqiang.day0714_gisim.api.FindApi;
import com.aqiang.day0714_gisim.mvp.contract.FriendsContract;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FriendsService implements FriendsContract.FriendsModel {
    @Override
    public Observable<BaseResponseEntity<List<UserEntity>>> getFriends(String usercode) {
        return RetrofitManager.getInstance().create(FindApi.class).getFriends(usercode);
    }
}
