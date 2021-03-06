package com.aqiang.day0714_gisim.mvp.view.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.adapter.FindAdapter;
import com.aqiang.day0714_gisim.adapter.VpAddAdapter;
import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.day0714_gisim.mvp.presenter.FindPresenter;
import com.aqiang.storage.sp.SPUtils;
import com.aqiang.usermodel.entity.UserEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends BaseActivity{

    private TabLayout mTabActAdd;
    private ViewPager mVpActAdd;

    @Override
    protected int bindLayout() {
        return R.layout.activity_add;
    }

    @Override
    protected void createPresenter() {
        //mBasePresenter = new FindPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTabActAdd = (TabLayout) findViewById(R.id.tab_act_add);
        mVpActAdd = (ViewPager) findViewById(R.id.vp_act_add);
    }

    @Override
    protected void initData() {
        mVpActAdd.setAdapter(new VpAddAdapter(getSupportFragmentManager()));
//        list = new ArrayList<>();
//        findAdapter = new FindAdapter(R.layout.item_find,list);
//        mRvActAdd.setAdapter(findAdapter);
//        findAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                //showToast(""+SPUtils.getInstance().getObject("userCode","11"));
//                mBasePresenter.addFriend((String) SPUtils.getInstance().getObject("userCode","11"),list.get(position).getUsercode());
//            }
//        });
    }

    @Override
    protected void initEvent() {

        mTabActAdd.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVpActAdd.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        mEtActAddFriends.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
//                    String s = v.getText().toString();
//                    mBasePresenter.findFriend(s,"enen");
//                }
//                return false;
//            }
//        });
//        mEtActAddFriends.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                String s1 = s.toString();
////                if(s1.equals("44")){
////                    mBasePresenter.findFriend(s1,"enen");
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                String string = s.toString();
//                if(string.length() == 0){
//                    list.clear();
//                    findAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//
//        mTvActAddPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AddActivity.this,PhoneActivity.class));
//            }
//        });
//
//        mTvActAddFace.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AddActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });
    }

//    @Override
//    public void setAdapter(List<UserEntity> userEntities) {
//        list.addAll(userEntities);
//        findAdapter.notifyDataSetChanged();
//    }
}
