package com.aqiang.day0714_gisim.mvp.view.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aqiang.common.widget.TitleBar;
import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.MainActivity;
import com.aqiang.day0714_gisim.R;
import com.aqiang.day0714_gisim.adapter.MsgAdapter;
import com.aqiang.day0714_gisim.audio.LameMp3Manager;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.day0714_gisim.mvp.contract.MsgContract;
import com.aqiang.day0714_gisim.mvp.presenter.MsgPresenter;
import com.aqiang.day0714_gisim.sql.MsgSql;
import com.aqiang.storage.sp.SPUtils;
import com.baweigame.xmpplibrary.XmppManager;

import org.jivesoftware.smack.chat2.Chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SendMsgActivity extends BaseActivity<MsgPresenter> implements MsgContract.MsgView {
    private MsgAdapter msgAdapter;
    private List<MsgEntity> list;
    private TitleBar mTitleActSendMsgName;
    private RecyclerView mRvActSendMsg;
    private EditText mEtActSendMsg;
    private Button mBtActSendMsg;
    private ImageView mIvActSendMsgWheat;
    private ImageView mIvActSendMsgPic;
    private String username;
    private String user;
    private LinearLayoutManager linearLayoutManager;
    private SQLiteDatabase db;
    private ImageView mIvActSendMsgPhoto;
    private boolean isStart = false;
    @Override
    protected int bindLayout() {
        return R.layout.activity_send_msg;
    }

    @Override
    protected void createPresenter() {
        mBasePresenter = new MsgPresenter(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mTitleActSendMsgName = (TitleBar) findViewById(R.id.title_act_send_msg_name);
        mRvActSendMsg = (RecyclerView) findViewById(R.id.rv_act_send_msg);
        mEtActSendMsg = (EditText) findViewById(R.id.et_act_send_msg);
        mBtActSendMsg = (Button) findViewById(R.id.bt_act_send_msg);
        mIvActSendMsgWheat = (ImageView) findViewById(R.id.iv_act_send_msg_wheat);
        mIvActSendMsgPic = (ImageView) findViewById(R.id.iv_act_send_msg_pic);
        linearLayoutManager = new LinearLayoutManager(this);
        mRvActSendMsg.setLayoutManager(linearLayoutManager);
        mIvActSendMsgPhoto = (ImageView) findViewById(R.id.iv_act_send_msg_photo);
    }

    @Override
    protected void initData() {
        MsgSql msgSql = new MsgSql(this);
        db = msgSql.getReadableDatabase();
        list = new ArrayList<>();
        msgAdapter = new MsgAdapter(list);
        mRvActSendMsg.setAdapter(msgAdapter);
        linearLayoutManager.scrollToPosition(list.size() - 1);
        username = getIntent().getStringExtra("username");
        if(!TextUtils.isEmpty(username)){
            mTitleActSendMsgName.setTitleText(username);
        }
        user = (String) SPUtils.getInstance().getObject("user", "11");
        mBasePresenter.getMsg(user,username);
    }

    @Override
    protected void initEvent() {
        mBtActSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MsgEntity msgEntity = new MsgEntity();
                msgEntity.setFromuser(user);
                msgEntity.setTouser(username);
                msgEntity.setMsg(mEtActSendMsg.getText().toString());

//                String friend = username + "@" + XmppManager.getInstance().getConnection().getServiceName();
//                String msg = mEtActSendMsg.getText().toString();
//                Chat friendChat = XmppManager.getInstance().getXmppMsgManager().getFriendChat(friend);
//                XmppManager.getInstance().getXmppMsgManager().sendSingleMessage(friendChat, msg);

                //String mine = (String) SPUtils.get(SendMsgActivity.this, "currentuser", "");
                //msgEntities.add(new MsgEntity(mine, etMainFriendPhonenumber.getText().toString(), msg, MsgEntity.MsgType.Txt));
                //myAdapter.notifyDataSetChanged();

                mBasePresenter.addMsg(msgEntity);
                list.add(msgEntity);
                if(msgAdapter != null){
                    msgAdapter.notifyDataSetChanged();
                }
                //mBasePresenter.getMsg(user,username);
                mEtActSendMsg.setText("");
            }
        });


        mIvActSendMsgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SendMsgActivity.this,VideoActivity.class));
            }
        });

        mIvActSendMsgWheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStart){
                    LameMp3Manager.instance.startRecorder(Environment.getExternalStoragePublicDirectory("DCIM").getAbsolutePath() + ".mp3");
                    isStart = true;
                }else {
                    LameMp3Manager.instance.stopRecorder();
                    isStart = false;
                }
            }
        });
    }

    @Override
    public void initAdapter(List<MsgEntity> list) {
        this.list.clear();
        this.list.addAll(list);
        if(list != null && list.size() > 0){
            ContentValues values = new ContentValues();
            values.put("user",list.get(0).getTouser());
            values.put("msg",list.get(0).getMsg());
            String msgtime = list.get(0).getMsgtime();
            values.put("times",getTime(msgtime));
            db.insert("msg",null,values);
        }
        if(msgAdapter != null){
            msgAdapter.notifyDataSetChanged();
        }
    }

    private String getTime(String msgtime){
        String[] split = msgtime.split(" ");
        String year = split[0];
        String honer = split[1];
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(date);

        if(year.equals(format)){
            return year;
        }
        String[] split1 = honer.split(":");
        String hour = split1[0];
        if(split1[1].equals("00")){
            if(Integer.parseInt(hour) > 12){
               return "下午" + hour+ ":00";
            }else {
                return "上午" +hour+ ":00";
            }
        }else {
            if(Integer.parseInt(hour) > 12){
                return "下午" + (Integer.parseInt(hour) + 1) + ":00";
            }else {
                return "上午" + (Integer.parseInt(hour) + 1) + ":00";
            }
        }
    }

}
