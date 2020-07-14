package com.aqiang.net.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.net.BaseResponseEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseResponseEntity<R>>> {
    private Type type;

    public LiveDataCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<BaseResponseEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseResponseEntity<R>> liveData = new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if(Thread.currentThread() == Looper.getMainLooper().getThread()){
                    liveData.setValue((BaseResponseEntity<R>) response.body());
                }else {
                    liveData.postValue((BaseResponseEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if(Thread.currentThread() == Looper.getMainLooper().getThread()){
                    liveData.setValue(null);
                }else {
                    liveData.postValue(null);
                }
            }
        });
        return liveData;
    }
}
