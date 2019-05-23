package com.lee.helper.advancedandroidhelper.utils.tuyahome;

import java.util.List;


/**
 * provide Observer and abstract method.
 * subclass : DevDataManager
 * */
public abstract class AbsDataManager<T,V>{

    protected List<V> v;
    protected DataObserver observer;
    public abstract AbsDataManager data(List<T> t);

    public void subscribe( DataObserver observer ){
        this.observer = observer;
    }

    public  interface DataObserver<K>{
        void onNext(List<K> k);
    }
}
