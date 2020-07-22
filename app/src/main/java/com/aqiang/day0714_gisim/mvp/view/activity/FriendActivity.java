package com.aqiang.day0714_gisim.mvp.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.aqiang.common.widget.OnTitleBarViewClickListener;
import com.aqiang.common.widget.TitleBar;
import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.MainActivity;
import com.aqiang.day0714_gisim.R;
import com.baweigame.xmpplibrary.XmppManager;
import com.baweigame.xmpplibrary.callback.IAddFriendCallback;

import org.jxmpp.jid.Jid;

public class FriendActivity extends BaseActivity {
    private TitleBar mTitleActFri;
    private RecyclerView mRvActMainAdd;
    private TabLayout mTabActMain;
    private RecyclerView mRvActMainFriends;

    @Override
    protected int bindLayout() {
        return R.layout.activity_friend;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mTitleActFri = (TitleBar) findViewById(R.id.title_act_fri);
        mRvActMainAdd = (RecyclerView) findViewById(R.id.rv_act_main_add);
        mTabActMain = (TabLayout) findViewById(R.id.tab_act_main);
        mRvActMainFriends = (RecyclerView) findViewById(R.id.rv_act_main_friends);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mTitleActFri.setOnTitleBarViewClickListener(new OnTitleBarViewClickListener() {
            @Override
            public void leftListener() {

            }

            @Override
            public void rightListener() {
                startActivity(new Intent(FriendActivity.this,AddActivity.class));
            }
        });

        XmppManager.getInstance().setAddFriendCallback(new IAddFriendCallback() {
            @Override
            public void notify(final Jid jid) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendActivity.this);
                builder.setTitle(jid.asBareJid() + "添加您为好友是否同意?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        XmppManager.getInstance().getXmppFriendManager().removeFriend(jid.asBareJid().toString());
                    }
                });
                builder.setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       showToast("已成功添加好友");
                    }
                });
            }
        });
    }
}
