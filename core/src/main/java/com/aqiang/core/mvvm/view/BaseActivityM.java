package com.aqiang.core.mvvm.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aqiang.core.mvvm.viewmodel.BaseViewModel;

public abstract class BaseActivityM<Binding extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {
    protected Binding binding;
    protected VM vm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,bindLayout());
        vm = createVM();
        setBinding();
    }

    protected void showToast(String msg){
        Toast.makeText(this, ""+msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract int bindLayout();

    protected abstract VM createVM();

    protected abstract void setBinding();
}
