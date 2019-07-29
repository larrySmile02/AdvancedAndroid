package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.PluralsRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.TestViewPagerAdapter;
import com.lee.helper.advancedandroidhelper.fragments.ViewPagerFragment1;
import com.lee.helper.advancedandroidhelper.fragments.ViewPagerFragment2;

import java.util.ArrayList;
import java.util.List;

public class TestFragmentPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private TestViewPagerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragmentpager);
        initData();
        initViews();

    }

    private void initData(){
//        Fragment ft1 = new ViewPagerFragment1();
//        Fragment ft2 = new ViewPagerFragment2();
//        fragments.add(ft1);
//        fragments.add(ft2);

        for(int i = 1; i< 5; i++){
            Fragment fragment = new ViewPagerFragment1();
            Bundle bundle = new Bundle();
            bundle.putInt("POSTION",i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }


    private void initViews(){
        viewPager = findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        adapter = new TestViewPagerAdapter(fm,fragments);
        viewPager.setAdapter(adapter);

    }
}
