package com.aqiang.usermodel.mvp.model;

import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.mvp.contract.UserContract;
import com.aqiang.usermodel.mvp.model.service.UserService;

import io.reactivex.Observable;

public class UserRepository extends UserContract.UserRepository {
    @Override
    public Observable<BaseResponseEntity<UserEntity>> login(UserEntity userEntity) {
        return mModel.login(userEntity);
    }

    @Override
    public Observable<BaseResponseEntity<UserEntity>> register(UserEntity userEntity) {
        return mModel.register(userEntity);
    }

    @Override
    protected void createModel() {
        mModel = new UserService();
    }
}
