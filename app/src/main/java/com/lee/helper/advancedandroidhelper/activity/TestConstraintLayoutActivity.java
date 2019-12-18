package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.RecTestAdapter;
import com.lee.helper.advancedandroidhelper.utils.ConstraintUtil;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class TestConstraintLayoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConstraintUtil util;
    private ConstraintSet constraintSet;
    private List<String> list = new ArrayList<>();
    private RecTestAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atiivty_constraint);
        initData();
        recyclerView = findViewById(R.id.config_all_dms_rv_right);
        GridLayoutManager manager =   new GridLayoutManager(this, 2, VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        initConstraint();
        adapter = new RecTestAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }

    private void initConstraint(){

        constraintSet = new ConstraintSet();
        ConstraintLayout layout = (ConstraintLayout) recyclerView.getParent();
        constraintSet.clone(layout);
        constraintSet.centerHorizontallyRtl(R.id.config_all_dms_rv_right,ConstraintSet.PARENT_ID);
        constraintSet.constrainWidth(R.id.config_all_dms_rv_right,ConstraintSet.WRAP_CONTENT);
        constraintSet.applyTo(layout);

    }

    private void initData(){
        list.add("num 1");
        list.add("num 2");
        list.add("num 3");
    }
}
