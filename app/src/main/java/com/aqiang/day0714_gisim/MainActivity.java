package com.aqiang.day0714_gisim;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.common.widget.BNViewGroup;
import com.aqiang.common.widget.OnViewClickListener;
import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.mvp.view.activity.FriendActivity;
import com.aqiang.storage.sp.SPUtils;

@Route(path = RouterPath.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {
    private AMap aMap;
    private MapView mMapActMain;
    private DrawerLayout mDlActMain;
    private NavigationView mNvActMain;
    private Button mbtLogin;
    private TextView mtvName;
    private MyLocationStyle myLocationStyle;
    private BNViewGroup mBnviewgroupActMain;
    private ImageView mIvActMainMsg;

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_CONTACTS,
                    "android.permission.ACCESS_COARSE_LOCATION",
                    "android.permission.READ_PHONE_STATE",
                    Manifest.permission.CAMERA
            },0);
        }
        mMapActMain = (MapView) findViewById(R.id.map_act_main);
        mMapActMain.onCreate(savedInstanceState);
        mDlActMain = (DrawerLayout) findViewById(R.id.dl_act_main);
        mNvActMain = (NavigationView) findViewById(R.id.nv_act_main);
        mDlActMain.setScrimColor(Color.TRANSPARENT);
        View view = mNvActMain.getHeaderView(0);
        mbtLogin = view.findViewById(R.id.bt_header_login);
        mtvName = view.findViewById(R.id.tv_header_name);
        mIvActMainMsg = (ImageView) findViewById(R.id.iv_act_main_msg);
        if(SPUtils.getInstance().contains("isLogin")){
            mbtLogin.setVisibility(View.GONE);
            mtvName.setVisibility(View.VISIBLE);
            mtvName.setText((String) SPUtils.getInstance().getObject("user","xx用户"));
        }
        mBnviewgroupActMain = (BNViewGroup) findViewById(R.id.bnviewgroup_act_main);
        mBnviewgroupActMain.showView(mIvActMainMsg);
    }

    @Override
    protected void initData() {
        if (aMap == null) {
            aMap = mMapActMain.getMap();
        }
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);//
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                double longitude = location.getLongitude();
                double latitude = location.getLatitude();

            }
        });
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
        mBnviewgroupActMain.setListener(new OnViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                if (position == 0){
                    startActivity(new Intent(MainActivity.this,FriendActivity.class));
                }else if(position == 1){
                    showToast("22");
                }else if(position == 2){
                    showToast("33");
                }else if(position == 3){
                    showToast("44");
                }else if(position == 4){
                    showToast("55");
                }
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
