package com.aqiang.day0714_gisim.adapter;

import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MainUserAdapter extends BaseQuickAdapter<MsgEntity, BaseViewHolder> {
    public MainUserAdapter(int layoutResId, @Nullable List<MsgEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgEntity item) {
        Glide.with(mContext)
            .load(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.user_w))
            .transform(new CircleCrop())
            .into((ImageView) helper.getView(R.id.iv_item_main));
    }
}
