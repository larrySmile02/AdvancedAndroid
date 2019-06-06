package com.lee.helper.advancedandroidhelper.model;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.lee.helper.advancedandroidhelper.bean.NewsBean;
import com.lee.helper.advancedandroidhelper.bean.NewsItem;
import com.lee.helper.smartokhttp.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsViwModel extends ViewModel {
    public static final String url = "https://v.juhe.cn/toutiao/index?type=top&key=483294d5e9b2202317817d0696b47a58";
    private final String API_KEY = "483294d5e9b2202317817d0696b47a58";
    private MutableLiveData<List<NewsItem>> liveDataNews;
    private Gson gson;
    private NewsViwModelBuilder builder;

    public NewsViwModelBuilder builder (){
        if(builder == null){
            builder = new NewsViwModelBuilder();
        }
        return builder;
    }

    public  MutableLiveData<List<NewsItem>> getLiveDataNew(){
        if(liveDataNews == null){
            synchronized (NewsViwModel.class){
                if(liveDataNews == null){
                    liveDataNews = new MutableLiveData<>();
                    gson = new Gson();
                }
            }
        }
        return liveDataNews;
    }

    public MutableLiveData<List<NewsItem>> registerObserver(AppCompatActivity mActivity, Observer<List<NewsItem>> observer){
        if(liveDataNews != null) liveDataNews.observe(mActivity,observer);
        return liveDataNews;
    }

    public NewsViwModel build(){
        return this;
    }

    public void getData(String url){
        HttpUtils.get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                List<NewsItem> items = null;
                if(!TextUtils.isEmpty(json)){
                    NewsBean newsBean = gson.fromJson(json,NewsBean.class);
                    items = newsBean.getResult().getData();

                }
               liveDataNews.postValue(items);
            }
        });
    }

   public class NewsViwModelBuilder{
        private AppCompatActivity mActivity;
       private MutableLiveData<List<NewsItem>> liveData;
       private Observer<List<NewsItem>> observer;
       public NewsViwModelBuilder getLiveData(){
           this.liveData = getLiveDataNew();
           return this;
       }

       public NewsViwModelBuilder observer(AppCompatActivity activity,Observer observer){
           this.observer = observer;
           this.mActivity = activity;
           return this;
       }

       public NewsViwModelBuilder build(){
           liveData.observe(mActivity,observer);
           return this;
       }

       public void data(String url){
           getData(url);
       }


    }

}
