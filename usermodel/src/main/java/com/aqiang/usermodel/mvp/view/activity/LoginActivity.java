package com.aqiang.usermodel.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.common.NetHelper;
import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.storage.sp.SPUtils;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.mvp.contract.UserContract;
import com.aqiang.usermodel.mvp.presenter.UserPresenter;
import com.baweigame.xmpplibrary.XmppManager;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
@Route(path = RouterPath.USER_LOGIN)
public class LoginActivity extends BaseActivity<UserPresenter> implements UserContract.UserView {

    private EditText mEtActLoginUser;
    private EditText mEtActLoginPwd;
    private TextView mTvActLoginRegister;
    private ImageButton mIbActLoginQq;
    private Button mBtnActLogin;

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {
        mBasePresenter = new UserPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mEtActLoginUser = (EditText) findViewById(R.id.et_act_login_user);
        mEtActLoginPwd = (EditText) findViewById(R.id.et_act_login_pwd);
        mTvActLoginRegister = (TextView) findViewById(R.id.tv_act_login_register);
        mIbActLoginQq = (ImageButton) findViewById(R.id.ib_act_login_qq);
        mBtnActLogin = (Button) findViewById(R.id.btn_act_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mBtnActLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(getUser());
                userEntity.setPwd(getPwd());
                NetHelper.doTask(new Runnable() {
                    @Override
                    public void run() {
                        boolean login = XmppManager.getInstance().getXmppUserManager().login(getUser(), getPwd());
                        if(login){
                            Log.d("swq","成功");
                        }
                    }
                });
                mBasePresenter.login(userEntity);
            }
        });
        mTvActLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        mIbActLoginQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Platform plat = ShareSDK.getPlatform(QQ.NAME);
//移除授权状态和本地缓存，下次授权会重新授权
                plat.removeAccount(true);
//SSO授权，传false默认是客户端授权
                plat.SSOSetting(false);
//授权回调监听，监听oncomplete，onerror，oncancel三种状态
                plat.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        RouterManager.router(RouterPath.MAIN_ACTIVITY);
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
//抖音登录适配安卓9.0
//ShareSDK.setActivity(MainActivity.this);
//要数据不要功能，主要体现在不会重复出现授权界面
                plat.showUser(null);
            }
        });
    }

    @Override
    public String getUser() {
        return mEtActLoginUser.getText().toString().trim();
    }

    @Override
    public String getPwd() {
        return mEtActLoginPwd.getText().toString().trim();
    }

    @Override
    public void succee() {
        SPUtils.getInstance().put("user",getUser());
        SPUtils.getInstance().put("isLogin",true);
        showToast("登陆成功");
        RouterManager.router(RouterPath.MAIN_ACTIVITY);
    }
}
