package com.aqiang.core.mvp;

import com.aqiang.core.mvp.model.IModel;

public abstract class Repository<M extends IModel> {
    protected M mModel;

    public Repository() {
        createModel();
    }

    protected abstract void createModel();
}
