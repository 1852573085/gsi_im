package com.aqiang.day0714_gisim.mvp.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.adapter.MsgUserAdapter;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.day0714_gisim.sql.MsgSql;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends BaseActivity {
    private List<MsgEntity> list;
    private RecyclerView mRvActMsg;
    private SQLiteDatabase db;
    private MsgUserAdapter msgUserAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.activity_msg;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mRvActMsg = (RecyclerView) findViewById(R.id.rv_act_msg);
        mRvActMsg.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        MsgSql msgSql = new MsgSql(this);
        db = msgSql.getReadableDatabase();
        Cursor cursor = db.query("msg", null, null, null, "user", null, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                String user = cursor.getString(cursor.getColumnIndex("user"));
                String msg = cursor.getString(cursor.getColumnIndex("msg"));
                String times = cursor.getString(cursor.getColumnIndex("times"));
                MsgEntity msgEntity = new MsgEntity();
                msgEntity.setTouser(user);
                msgEntity.setMsg(msg);
                msgEntity.setMsgtime(times);
                if(!list.contains(msgEntity)){
                    list.add(msgEntity);
                }
            }
            cursor.close();
        }
        if (msgUserAdapter == null){
            msgUserAdapter = new MsgUserAdapter(R.layout.item_msg,list);
            mRvActMsg.setAdapter(msgUserAdapter);
            msgUserAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent(MsgActivity.this, SendMsgActivity.class);
                    intent.putExtra("username",list.get(position).getTouser());
                    startActivity(intent);
                }
            });
        }else {
            msgUserAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initEvent() {

    }

}
