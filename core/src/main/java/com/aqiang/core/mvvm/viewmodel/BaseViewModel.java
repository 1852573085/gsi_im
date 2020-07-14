package com.aqiang.core.mvvm.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvvm.RepositoryM;

import java.util.HashMap;

public class BaseViewModel extends ViewModel implements LifecycleObserver {
    protected HashMap<String, RepositoryM> repositoryMHashMap;

    public BaseViewModel(LifecycleOwner owner) {
        repositoryMHashMap = new HashMap<>();
    }

    protected void registerRepository(String key, RepositoryM repository){
        if(repositoryMHashMap.containsKey(key)){
            return;
        }
        repositoryMHashMap.put(key,repository);
    }

    protected void nuRegisterRepository(String key){
        if(repositoryMHashMap.containsKey(key)){
            repositoryMHashMap.remove(key);
        }
    }

    protected <Sub extends RepositoryM> Sub getRepository(String key){
        if(repositoryMHashMap.containsKey(key)){
            return (Sub) repositoryMHashMap.get(key);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        repositoryMHashMap.clear();
        repositoryMHashMap = null;
    }

}
