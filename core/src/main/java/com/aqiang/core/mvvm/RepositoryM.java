package com.aqiang.core.mvvm;

import android.arch.lifecycle.LifecycleOwner;

import com.aqiang.core.mvvm.model.IModelM;

public abstract class RepositoryM<M extends IModelM> {
    protected M mModel;
    protected LifecycleOwner owner;
    public RepositoryM(LifecycleOwner owner) {
        createModel();
        this.owner = owner;
    }

    protected abstract void createModel();
}
