package com.aqiang.day0714_gisim.mvp.model.service;

import com.aqiang.day0714_gisim.api.FindApi;
import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FindService implements FindContract.FindModel {
    @Override
    public Observable<BaseResponseEntity<List<UserEntity>>> findFriend(String username, String nick) {
        return RetrofitManager.getInstance().create(FindApi.class).findFriend(username, nick);
    }

    @Override
    public Observable<BaseResponseEntity<Boolean>> addFriend(String usercode, String friendcode) {
        return RetrofitManager.getInstance().create(FindApi.class).addFriend(usercode, friendcode);
    }
}
