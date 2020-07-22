package com.aqiang.day0714_gisim.mvp.view.activity;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.adapter.PhoneAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends BaseActivity {
    private List<String> list;
    private RecyclerView mRvActPhone;
    private PhoneAdapter phoneAdapter;
    @Override
    protected int bindLayout() {
        return R.layout.activity_phone;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRvActPhone = (RecyclerView) findViewById(R.id.rv_act_phone);
        mRvActPhone.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,null);
        if(cursor != null){
            while (cursor.moveToNext()){
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                if(!list.contains(name)){
                    list.add(name);
                }
            }
        }
        cursor.close();
        if(phoneAdapter == null){
            phoneAdapter = new PhoneAdapter(R.layout.item_phone,list);
            mRvActPhone.setAdapter(phoneAdapter);
        }else {
            phoneAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initEvent() {

    }
}
