package com.lee.helper.advancedandroidhelper.utils.tuyahome;

import java.util.List;

/**
 * 1.Get origin data
 * 2.Control main logic
 * */
public class HomeModel
{
    AbsDataManager<DeviceBean, HomeItemUIBean> absDataManager; //假装已经初始化
    List<DeviceBean> deviceBeans; //假装已经初始化

    public void DataDeal(){
        absDataManager.
                data(deviceBeans).
                subscribe(k -> {
                   //刷新UI
                });

    }
}
