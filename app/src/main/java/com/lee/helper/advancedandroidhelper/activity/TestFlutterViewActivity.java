package com.lee.helper.advancedandroidhelper.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import io.flutter.app.FlutterActivity;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;


public class TestFlutterViewActivity extends FlutterActivity implements LifecycleOwner
{
    private LifecycleRegistry mLifecycleRegistry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);


    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    @Override
    public FlutterView createFlutterView(Context context) {

        WindowManager.LayoutParams matchParent = new WindowManager.LayoutParams(-1, -1);
        FlutterNativeView nativeView = this.createFlutterNativeView();
        FlutterView flutterView = new FlutterView(this,  null, nativeView);
        flutterView.setInitialRoute("route1");  //这边可以更改第一次进去的路由界面
        flutterView.setLayoutParams(matchParent);
        setContentView(flutterView);
        return flutterView;
    }

    @Override
    public FlutterNativeView createFlutterNativeView() {
        return null;
    }
}
