package com.lee.helper.advancedandroidhelper.utils.tuyahome;

import java.util.List;

/**
 * Deal data and use observer
 * */
public class DevDataManager extends AbsDataManager<DeviceBean ,HomeItemUIBean>
{


    @Override
    public AbsDataManager data(List<DeviceBean> deviceBeans) {
        v = transfer(deviceBeans);
        observer.onNext(v); //说明数据有变化，果断刷新
        return this;
    }

    public List<HomeItemUIBean> transfer(List<DeviceBean> list){
        List<HomeItemUIBean> uiBeans = null; //假装是转换好的数据
        return uiBeans;
    }
}
