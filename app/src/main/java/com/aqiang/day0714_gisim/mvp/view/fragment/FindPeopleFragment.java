package com.aqiang.day0714_gisim.mvp.view.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.aqiang.core.mvp.view.BaseFragment;
import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.adapter.FindAdapter;
import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.day0714_gisim.mvp.presenter.FindPresenter;
import com.aqiang.day0714_gisim.mvp.view.activity.AddActivity;
import com.aqiang.day0714_gisim.mvp.view.activity.PhoneActivity;
import com.aqiang.storage.sp.SPUtils;
import com.aqiang.usermodel.entity.UserEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

public class FindPeopleFragment extends BaseFragment<FindPresenter> implements FindContract.FindView {
    private EditText mEtFragFindPeoFriends;
    private TextView mTvFragFindPeoAddPhone;
    private TextView mTvFragFindPeoAddFace;
    private RecyclerView mRvFragFindPeoAdd;
    private FindAdapter findAdapter;
    private List<UserEntity> list;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_find_people;
    }

    @Override
    protected void createPresenter() {
        mBasePresenter = new FindPresenter(this);
    }

    @Override
    protected void initView() {

        mEtFragFindPeoFriends = (EditText) findViewById(R.id.et_frag_find_peo_friends);
        mTvFragFindPeoAddPhone = (TextView) findViewById(R.id.tv_frag_find_peo_add_phone);
        mTvFragFindPeoAddFace = (TextView) findViewById(R.id.tv_frag_find_peo_add_face);
        mRvFragFindPeoAdd = (RecyclerView) findViewById(R.id.rv_frag_find_peo_add);
        mRvFragFindPeoAdd.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        findAdapter = new FindAdapter(R.layout.item_find,list);
        mRvFragFindPeoAdd.setAdapter(findAdapter);
        findAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //showToast(""+SPUtils.getInstance().getObject("userCode","11"));
                mBasePresenter.addFriend((String) SPUtils.getInstance().getObject("userCode","11"),list.get(position).getUsercode());
            }
        });
    }

    @Override
    protected void initEvent() {
        mEtFragFindPeoFriends.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    String s = v.getText().toString();
                    mBasePresenter.findFriend(s,"enen");
                }
                return false;
            }
        });

        mEtFragFindPeoFriends.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String s1 = s.toString();
//                if(s1.equals("44")){
//                    mBasePresenter.findFriend(s1,"enen");
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String string = s.toString();
                if(string.length() == 0){
                    list.clear();
                    findAdapter.notifyDataSetChanged();
                }
            }
        });

        mTvFragFindPeoAddPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PhoneActivity.class));
            }
        });

        mTvFragFindPeoAddFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public void setAdapter(List<UserEntity> userEntities) {
        list.addAll(userEntities);
        findAdapter.notifyDataSetChanged();
    }
}
