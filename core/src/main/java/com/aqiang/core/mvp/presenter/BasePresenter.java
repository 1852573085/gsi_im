package com.aqiang.core.mvp.presenter;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.view.IView;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<R extends Repository,V extends IView> {
    protected R mRepository;
    protected WeakReference<V> mView;

    public BasePresenter(V v) {
        createRepository();
        mView = new WeakReference<>(v);
    }

    protected abstract void createRepository();
}
