package com.lee.helper.advancedandroidhelper.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.RecNewsAdapter;
import com.lee.helper.advancedandroidhelper.bean.NewsItem;
import com.lee.helper.advancedandroidhelper.model.NewsViwModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsActivity extends AppCompatActivity {
    private String url = "https://v.juhe.cn/toutiao/index?type=top&key=483294d5e9b2202317817d0696b47a58";
    private Map<String, String> params = new HashMap<>();

    private RecyclerView recView;
    private RecNewsAdapter adapter;
    private List<NewsItem> itemList = new ArrayList<>();
    private NewsViwModel viwModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
        initData();

    }

    private void initView() {
        recView = findViewById(R.id.rec_news);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecNewsAdapter(this, itemList);
        recView.setAdapter(adapter);
    }

    private void initData() {
        viwModel = ViewModelProviders.of(this).get(NewsViwModel.class);
        Observer<List<NewsItem>> observer = newsItems -> {
            itemList.clear();
            itemList.addAll(newsItems);
            adapter.notifyDataSetChanged();
        };
        viwModel.builder().
                getLiveData().
                observer(this, observer).
                build().
                data(NewsViwModel.url);
    }


}
