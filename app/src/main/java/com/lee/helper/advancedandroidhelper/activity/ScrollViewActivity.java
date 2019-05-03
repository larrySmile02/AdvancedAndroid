package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.ViewPagerAdapter;
import com.lee.helper.advancedandroidhelper.custom.HorizotalScrollTab;

import java.util.Arrays;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity
{
    private HorizotalScrollTab scrollTab;
    private String [] items = new String[]{"All","First Page","Second Page","Third Page","Fourth Page","Fifth Page","Sixth Page"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_view);
        scrollTab = findViewById(R.id.horizotal_tab);
        List<String> datas = Arrays.asList(items);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,datas);
        viewPager.setAdapter(adapter);
        scrollTab.setViewPager(viewPager);


    }
}
