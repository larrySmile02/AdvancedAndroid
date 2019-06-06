package com.lee.helper.advancedandroidhelper.bean;

import java.util.ArrayList;

public class NewsResult {
   private  String stat; //":"1",
   private ArrayList<NewsItem> data; //":

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public ArrayList<NewsItem> getData() {
        return data;
    }

    public void setData(ArrayList<NewsItem> data) {
        this.data = data;
    }
}
