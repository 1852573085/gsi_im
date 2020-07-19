package com.aqiang.day0714_gisim;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.maps.MapView;
import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.storage.sp.SPUtils;

@Route(path = RouterPath.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {

    private MapView mMapActMain;
    private DrawerLayout mDlActMain;
    private NavigationView mNvActMain;
    private Button mbtLogin;
    private TextView mtvName;


    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mMapActMain = (MapView) findViewById(R.id.map_act_main);
        mMapActMain.onCreate(savedInstanceState);
        mDlActMain = (DrawerLayout) findViewById(R.id.dl_act_main);
        mNvActMain = (NavigationView) findViewById(R.id.nv_act_main);
        mDlActMain.setScrimColor(Color.TRANSPARENT);
        View view = mNvActMain.getHeaderView(0);
        mbtLogin = view.findViewById(R.id.bt_header_login);
        mtvName = view.findViewById(R.id.tv_header_name);
        if(SPUtils.getInstance().contains("isLogin")){
            mbtLogin.setVisibility(View.GONE);
            mtvName.setVisibility(View.VISIBLE);
            mtvName.setText((String) SPUtils.getInstance().getObject("user","xx用户"));
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mbtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.router(RouterPath.USER_LOGIN);
            }
        });
        mtvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.router(RouterPath.USER_LOGIN);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapActMain.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapActMain.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapActMain.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapActMain.onSaveInstanceState(outState);
    }
}
