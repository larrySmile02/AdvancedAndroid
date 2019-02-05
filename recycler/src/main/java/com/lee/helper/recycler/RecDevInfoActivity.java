package com.lee.helper.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.lee.helper.config.ConfigUIActivity;
import com.lee.helper.recycler.adapter.DevInfoAdapter;
import com.lee.helper.recycler.model.IDevItemModel;
import com.lee.helper.recycler.model.IRecView;
import com.lee.helper.recycler.presenter.RecPresenter;
import com.lee.helper.recycler.widget.SimpleDividerItemDoceration;

import java.util.List;

public class RecDevInfoActivity extends ConfigUIActivity implements IRecView{

    private RecyclerView recView;
    private DevInfoAdapter adapter;
    private RecPresenter presenter;

    @Override
    public int getConfigBase() {
        return CONFIG_HEIGH_BASE;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_info);
        initViews();
        presenter = new RecPresenter(this,this);
        presenter.requestItems();
    }

    private void initViews(){
        recView = findViewById(R.id.rec_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);
        SimpleDividerItemDoceration decoration = new SimpleDividerItemDoceration(this,1,R.color.recycler_bfbfbf,false);
        recView.addItemDecoration(decoration);
        adapter = new DevInfoAdapter(this);
        recView.setAdapter(adapter);
    }

    @Override
    public void update(List<IDevItemModel> devItems) {
        adapter.setItems(devItems);
        adapter.notifyDataSetChanged();
    }
}
