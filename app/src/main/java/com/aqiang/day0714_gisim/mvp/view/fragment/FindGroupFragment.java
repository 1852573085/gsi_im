package com.aqiang.day0714_gisim.mvp.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.aqiang.core.mvp.view.BaseFragment;
import com.aqiang.day0714_gisim.R;

public class FindGroupFragment extends BaseFragment {
    private EditText mEtFragFindGroFriends;
    private TextView mTvFragFindGroAddPhone;
    private TextView mTvFragFindGroAddFace;
    private RecyclerView mRvFragFindGroAdd;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_find_group;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {

        mEtFragFindGroFriends = (EditText) findViewById(R.id.et_frag_find_gro_friends);
        mTvFragFindGroAddPhone = (TextView) findViewById(R.id.tv_frag_find_gro_add_phone);
        mTvFragFindGroAddFace = (TextView) findViewById(R.id.tv_frag_find_gro_add_face);
        mRvFragFindGroAdd = (RecyclerView) findViewById(R.id.rv_frag_find_gro_add);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
