package com.aqiang.net.adapter;

import android.arch.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.Policy;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    public static LiveDataCallAdapterFactory create(){
        return new LiveDataCallAdapterFactory();
    }
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if(!(returnType instanceof ParameterizedType)){
            throw new IllegalArgumentException("返回值参数不能参数化");
        }
        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);
        if(rawType != Call.class || rawType != LiveData.class){
            throw new IllegalArgumentException("返回值参数类型不为LiveData|Call");
        }
        Type type = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);
        if(rawType == Call.class){
            return new DefaultCallAdapter<>(type);
        }
        return new LiveDataCallAdapter<>(type);
    }
}
