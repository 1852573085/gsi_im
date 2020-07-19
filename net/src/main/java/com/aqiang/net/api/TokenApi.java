package com.aqiang.net.api;

import com.aqiang.net.entity.TokenEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TokenApi {
    @FormUrlEncoded
    @POST("token")
    Call<TokenEntity> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
