package com.aqiang.day0714_gisim.adapter;

import android.support.annotation.Nullable;

import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MsgUserAdapter extends BaseQuickAdapter<MsgEntity, BaseViewHolder> {
    public MsgUserAdapter(int layoutResId, @Nullable List<MsgEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgEntity item) {
        helper.setText(R.id.tv_item_msg,item.getMsg());
        helper.setText(R.id.tv_item_msg_name,item.getTouser());
        helper.setText(R.id.tv_item_msg_time,item.getMsgtime());
    }
}
