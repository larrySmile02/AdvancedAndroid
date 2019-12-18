package com.lee.helper.advancedandroidhelper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.ViewPagerAdapter;
import com.lee.helper.advancedandroidhelper.custom.MyViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCustomActivity extends AppCompatActivity {

    private MyViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<String> list = new ArrayList<>();
    private String []titles = {"FIRST","SECOND","THIRD"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_activity);
        initData();
        viewPager = findViewById(R.id.my_viewpager);
        adapter = new ViewPagerAdapter(this,list);
        viewPager.setAdapter(adapter);
//        viewPager.setVertical(true);
        testAspect();

    }


    private void testAspect(){

        Log.e("TestCustomActivity","testAspect ===");

    }



    private void initData(){
       List<String> tempList =  Arrays.asList(titles);
       list.addAll(tempList);
    }



}
