package com.aqiang.day0714_gisim.adapter;

import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MsgAdapter extends BaseMultiItemQuickAdapter<MsgEntity, BaseViewHolder> {

    public MsgAdapter(List<MsgEntity> data) {
        super(data);
        addItemType(0, R.layout.item_msg_left);
        addItemType(1, R.layout.item_msg_right);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgEntity item) {
        if(helper.getItemViewType() == 0){
            helper.setText(R.id.tv_item_msg_left_time,item.getMsgtime());
            helper.setText(R.id.tv_item_msg_left_name,item.getFromuser());
            helper.setText(R.id.tv_item_msg_left_msg,item.getMsg());
        }else {
            helper.setText(R.id.tv_item_msg_right_time,item.getMsgtime());
            helper.setText(R.id.tv_item_msg_right_name,item.getFromuser());
            helper.setText(R.id.tv_item_msg_right_msg,item.getMsg());
        }
    }
}
