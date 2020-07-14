package com.aqiang.net;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
                    .baseUrl("")
                    .client(createClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }

    private OkHttpClient createClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(createLogInterceptor())
                .readTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(Config.TIME_OUT, TimeUnit.SECONDS)
                .build();
        return client;
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
