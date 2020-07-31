package com.aqiang.day0714_gisim.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.aqiang.day0714_gisim.mvp.view.fragment.FindGroupFragment;
import com.aqiang.day0714_gisim.mvp.view.fragment.FindPeopleFragment;

public class VpAddAdapter extends FragmentPagerAdapter {
    public VpAddAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
            return new FindPeopleFragment();
        }else if(i == 1){
            return new FindGroupFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
