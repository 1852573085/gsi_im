package com.aqiang.net;

import android.util.Log;

import com.aqiang.net.api.TokenApi;
import com.aqiang.net.entity.TokenEntity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private Retrofit retrofit;
    private RetrofitManager(){
        retrofit = initRetrofit();
    }

    private volatile static RetrofitManager retrofitManager;

    public static RetrofitManager getInstance(){
        if(retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    return new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private Retrofit initRetrofit() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.BASE_URL)
                    .client(createClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }

    private OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(createLogInterceptor())
                .addInterceptor(createTokenInterceptor())
                .readTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .build();
        return client;
    }

    private Interceptor createTokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if(response.code() == 401){
                    TokenEntity tokenEntity = getToken();
                    if(tokenEntity != null){
                        Request.Builder builder = request.newBuilder()
                                .addHeader("Authorization", tokenEntity.getToken_type() + " " + tokenEntity.getAccess_token());
                        Response proceed = chain.proceed(builder.build());
                        return proceed;
                    }
                }
                return response;
            }
        };
        return interceptor;
    }

    private TokenEntity getToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenEntity> token = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenEntity> execute = token.execute();
            return execute.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Interceptor createLogInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public <T> T create(Class<T> clazz){
        return retrofit.create(clazz);
    }
}
