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
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.i("ActivityCreated","onCreate = "+activity.toString());
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

    }
}
