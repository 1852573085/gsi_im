package com.aqiang.core.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aqiang.core.mvp.presenter.BasePresenter;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IView {
    protected P mBasePresenter;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(bindLayout(),container,false);
        return view;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createPresenter();
        initView();
        initData();
        initEvent();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    protected <T extends View> T findViewById(int id){
        return view.findViewById(id);
    }

    protected abstract int bindLayout();

    protected abstract void createPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();
}
