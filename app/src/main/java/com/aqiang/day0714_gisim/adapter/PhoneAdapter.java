package com.aqiang.day0714_gisim.adapter;

import android.support.annotation.Nullable;

import com.aqiang.day0714_gisim.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class PhoneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PhoneAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_item_phone,item);
    }
}
