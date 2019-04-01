package com.lee.helper.toast;

import android.app.Activity;
import android.app.Application;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/ToastUtils
 *    time   : 2018/11/02
 *    desc   : 不需要通知栏权限的 Toast
 */
final class SupportToast extends BaseToast {

    // 吐司弹窗显示辅助类
    private final ToastHelper mToastHelper;

    SupportToast(Application application) {
        super(application);
        mToastHelper = new ToastHelper(this, application);
    }

    SupportToast(Activity activity) {
        super(activity.getApplication());
        mToastHelper = new ToastHelper(this, activity);
    }

    @Override
    public void show() {
        // 显示吐司
        mToastHelper.show();
    }

    @Override
    public void cancel() {
        // 取消显示
        mToastHelper.cancel();
    }
}