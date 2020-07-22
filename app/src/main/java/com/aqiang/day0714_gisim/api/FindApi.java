package com.aqiang.day0714_gisim.api;

import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FindApi {
    @GET("api/Friend/searchFriend?")
    Observable<BaseResponseEntity<List<UserEntity>>> findFriend(@Query("username") String username, @Query("nick") String nick);
}
