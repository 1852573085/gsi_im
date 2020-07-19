package com.aqiang.usermodel.mvp.view.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.mvp.contract.UserContract;
import com.aqiang.usermodel.mvp.presenter.UserPresenter;

public class RegisterActivity extends BaseActivity<UserPresenter> implements UserContract.UserView {

    private EditText mEtActRegUser;
    private EditText mEtActRegPwd;
    private Button mBtnActReg;

    @Override
    protected int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void createPresenter() {
        mBasePresenter = new UserPresenter(this);
    }

    @Override
    protected void initView() {
        mEtActRegUser = (EditText) findViewById(R.id.et_act_reg_user);
        mEtActRegPwd = (EditText) findViewById(R.id.et_act_reg_pwd);
        mBtnActReg = (Button) findViewById(R.id.btn_act_reg);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mBtnActReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(getUser());
                userEntity.setPwd(getPwd());
                mBasePresenter.register(userEntity);
            }
        });
    }

    @Override
    public String getUser() {
        return mEtActRegUser.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtActRegPwd.getText().toString().trim();
    }

    @Override
    public void succee() {
        showToast("注册成功,可以登陆");
    }
}
