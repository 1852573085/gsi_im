package com.aqiang.usermodel.api;

import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi{
    @POST("api/User/login")
    Observable<BaseResponseEntity<UserEntity>> login(@Body UserEntity userEntity);

    @POST("api/User/register")
    Observable<BaseResponseEntity<UserEntity>> register(@Body UserEntity userEntity);
}
