package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.fragments.ViewPagerFragment1;

public class TestFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llt;
    private Button btn;
    private boolean isRemove = true;
    private ViewPagerFragment1 fragment ;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        initView();
        initFragment();
    }

    private void initView(){
        llt = findViewById(R.id.fragment_container);
        btn = findViewById(R.id.btn_remove);
        btn.setOnClickListener(this);
    }

    private void initFragment(){
        fragment = new ViewPagerFragment1();
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_remove){
            if(isRemove){
                fragmentManager = getSupportFragmentManager();
                transaction = fragmentManager.beginTransaction();
                transaction.detach(fragment);
                transaction.commit();
                isRemove = false;
                btn.setText("Add");
            }else {
                initFragment();
                isRemove = true;
                btn.setText("Remove");
            }
        }
    }
}
