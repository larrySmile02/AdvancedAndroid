package com.lee.helper.advancedandroidhelper.service;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.text.TextUtils;
import android.util.Log;

import com.lee.helper.advancedandroidhelper.constant.MyConstant;
import com.lee.helper.toast.ToastUtils;

import static com.lee.helper.advancedandroidhelper.constant.MyConstant.SEXY_GIRL;

public class MyJobService extends JobIntentService {

    private String TAG = MyJobService.class.getSimpleName();
    private String DEFAUT_GIRL = "仓老师";

    public static void equeue(Context mContext,Intent work){
        JobIntentService.enqueueWork(mContext, MyJobService.class,1001,work);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate<=========>");
    }


    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        String girl = DEFAUT_GIRL;
        if(!TextUtils.isEmpty(intent.getStringExtra(MyConstant.SEXY_GIRL)))
            girl = intent.getStringExtra(MyConstant.SEXY_GIRL);
        ToastUtils.show(girl);
        Log.e(TAG,"onHandleWork <=========>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy <=========>");
    }


}
