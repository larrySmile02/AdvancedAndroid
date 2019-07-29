package com.lee.helper.advancedandroidhelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

public class TestViewPagerAdapter extends FragmentStatePagerAdapter {

    private final String TAG = TestViewPagerAdapter.class.getSimpleName();

    List<Fragment> fragments;

    public TestViewPagerAdapter(FragmentManager fm , List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        Log.e(TAG,"getItem========");
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


}
