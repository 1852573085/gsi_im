package com.aqiang.core.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aqiang.core.mvp.presenter.BasePresenter;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {
    protected P mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());
        createPresenter();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract int bindLayout();

    protected abstract void createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();
}
