package com.lee.helper.advancedandroidhelper;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.lee.helper.advancedandroidhelper.adapter.RecMainAdapter;
import com.lee.helper.config.ConfigUIActivity;
import com.lee.helper.recycler.widget.SimpleDividerItemDoceration;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ConfigUIActivity {

    /** 默认分割线高度*/
    private final int DEFAUT_DIVIDER_HEIGHT = 1;
    private RecyclerView recView;
    private RecMainAdapter adapter;
    private String [] items = new String[]{"Config Demo","Toast Demo","RecyclerView Demo"};

    @Override
    public int getConfigBase() {
        return CONFIG_ORIGIN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        recView = findViewById(R.id.rec);
        List<String> itemList = Arrays.asList(items);
        adapter = new RecMainAdapter(this,itemList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SimpleDividerItemDoceration decoration = new SimpleDividerItemDoceration(this,DEFAUT_DIVIDER_HEIGHT,R.color.advance_bfbfbf,true);
        recView.setLayoutManager(layoutManager);
        recView.addItemDecoration(decoration);
        recView.setAdapter(adapter);

    }



}
