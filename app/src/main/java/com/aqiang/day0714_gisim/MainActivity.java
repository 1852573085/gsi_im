package com.aqiang.day0714_gisim;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.aqiang.common.imgLoader.ImgLoader;
import com.aqiang.common.imgLoader.setting.CircleSetting;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        ImgLoader.getInstance().imgLoad(this,CircleSetting.Builder().setCircle(true).setImageView(mIv).setUrlPath("https://dss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg").build());
    }
}
