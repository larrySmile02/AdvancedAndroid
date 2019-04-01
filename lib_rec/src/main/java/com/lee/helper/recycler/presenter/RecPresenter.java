package com.lee.helper.recycler.presenter;

import android.app.Activity;
import android.content.Context;

import com.lee.helper.recycler.beans.CheckItem;
import com.lee.helper.recycler.beans.ClickItem;
import com.lee.helper.recycler.beans.SpaceItem;
import com.lee.helper.recycler.model.IDevItemModel;
import com.lee.helper.recycler.model.IRecView;

import java.util.ArrayList;
import java.util.List;

public class RecPresenter  {

    private Activity mContext;
    private IRecView iRecView;
    private List<IDevItemModel> items = new ArrayList<>();
    public RecPresenter(Activity mContext , IRecView iRecView){
        this.mContext = mContext;
        this.iRecView = iRecView;
        initData();
    }

    public void requestItems(){
       iRecView.update(items);
    }

    private void initData(){
        ClickItem itemName = new ClickItem("Device name","Kitchen Cam",true);
        items.add(itemName);
        ClickItem itemInfo = new ClickItem("Device information","",true);
        items.add(itemInfo);
        SpaceItem itemSpace = new SpaceItem();
        items.add(itemSpace);
        ClickItem itemBicSetting = new ClickItem("Basic function settings","",true);
        items.add(itemBicSetting);
        CheckItem itemShare = new CheckItem("Shared from others",true);
        items.add(itemShare);
        ClickItem itemFeedBack = new ClickItem("Feedback","",true);
        items.add(itemFeedBack);
    }

}
