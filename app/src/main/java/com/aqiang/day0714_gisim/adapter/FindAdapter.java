package com.aqiang.day0714_gisim.adapter;

import android.support.annotation.Nullable;

import com.aqiang.day0714_gisim.R;
import com.aqiang.usermodel.entity.UserEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class FindAdapter extends BaseQuickAdapter<UserEntity, BaseViewHolder> {
    public FindAdapter(int layoutResId, @Nullable List<UserEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntity item) {
        helper.setText(R.id.tv_item_find_name,item.getUsername());
        helper.addOnClickListener(R.id.tv_item_find_add);
    }
}
