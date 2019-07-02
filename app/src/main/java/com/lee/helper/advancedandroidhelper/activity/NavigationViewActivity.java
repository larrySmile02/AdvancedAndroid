package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.NavigationAdapter;

import java.util.ArrayList;
import java.util.List;

public class NavigationViewActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private AppCompatImageView ivMenu;
    private ListView lvNav;
    private NavigationAdapter navAdapter;
    private List<String> navigationItems = new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_navigation);
        initData();
        initViews();

    }

    private void initData(){
        navigationItems.add(getString(R.string.ka_device_list));
        navigationItems.add(getString(R.string.ka_add_device));
        navigationItems.add(getString(R.string.ka_device_share));
        navigationItems.add(getString(R.string.ka_user_faq));
        navigationItems.add(getString(R.string.ka_account_set));
        navigationItems.add(getString(R.string.ka_third_use));
        navigationItems.add(getString(R.string.ka_help));
    }

    private void initViews(){
        drawer = findViewById(R.id.nbo_drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        LayoutInflater.from(this).inflate(R.layout.nav_header_menu,navigationView,true);
        ivMenu = findViewById(R.id.iv_title_menu);
        ivMenu.setOnClickListener(this);
        lvNav = findViewById(R.id.lv_nav);
        navAdapter = new NavigationAdapter(this,navigationItems);
        lvNav.setAdapter(navAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.iv_title_menu){
            drawer.openDrawer(GravityCompat.START);
        }
    }
}
