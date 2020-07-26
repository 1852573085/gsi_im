package com.aqiang.day0714_gisim.api;

import com.aqiang.day0714_gisim.entity.LocalEntity;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FindApi {
    @GET("api/Friend/searchFriend?")
    Observable<BaseResponseEntity<List<UserEntity>>> findFriend(@Query("username") String username, @Query("nick") String nick);

    //@FormUrlEncoded
    @POST("api/Friend/addFriend?")
    Observable<BaseResponseEntity<Boolean>> addFriend(@Query("usercode") String usercode, @Query("friendcode") String friendcode);

    @GET("api/Friend/getFriends?")
    Observable<BaseResponseEntity<List<UserEntity>>> getFriends(@Query("usercode") String usercode);

    @POST("api/UserLocation/uploadLocatoin")
    Observable<BaseResponseEntity<Boolean>> uploadLocal(@Body LocalEntity localEntity);

    @POST("api/Chat/addChatMsg")
    Observable<BaseResponseEntity<Boolean>> addMsg(@Body MsgEntity msgEntity);

    @GET("api/Chat/getChatMsg?")
    Observable<BaseResponseEntity<List<MsgEntity>>> getMsg(@Query("user1") String user1,@Query("user2") String user2,@Query("page") int page,@Query("pagesize") int pagesize);
}
