package com.lee.helper.advancedandroidhelper;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.lee.helper.toast.ToastUtils;
import com.lee.helper.toast.style.ToastBlackStyle;

public class MyApplication extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtils.init(this,new ToastBlackStyle());

    }
}
