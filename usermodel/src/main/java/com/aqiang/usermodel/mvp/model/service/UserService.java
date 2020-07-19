package com.aqiang.usermodel.mvp.model.service;

import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.usermodel.api.UserApi;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.mvp.contract.UserContract;

import io.reactivex.Observable;

public class UserService implements UserContract.UserModel {
    @Override
    public Observable<BaseResponseEntity<UserEntity>> login(UserEntity userEntity) {
        return RetrofitManager.getInstance().create(UserApi.class).login(userEntity);
    }

    @Override
    public Observable<BaseResponseEntity<UserEntity>> register(UserEntity userEntity) {
        return RetrofitManager.getInstance().create(UserApi.class).register(userEntity);
    }
}
