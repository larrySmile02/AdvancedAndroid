package com.lee.helper.advancedandroidhelper.activity;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.flutter.plugin.FlutterNativeDataPlugin;

import io.flutter.app.FlutterActivity;
import io.flutter.view.FlutterNativeView;
import io.flutter.view.FlutterView;

public class NativeGetDataActivity extends FlutterActivity implements LifecycleOwner {

    private LifecycleRegistry mLifecycleRegistry;
    private LinearLayout lltContainer;
    FlutterView flutterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_flutter_native_get);
        FlutterNativeDataPlugin.registerWith(this.registrarFor(FlutterNativeDataPlugin.CHANNEL));
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
//        lltContainer = findViewById(R.id.llt_flutter_forget);
//        lltContainer.addView(flutterView);
        Log.e("CHAOYUEMM","ONCREATE-->");

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
        flutterView = new FlutterView(this,  null, nativeView);
        flutterView.setInitialRoute("ForgotPwdPage");
        flutterView.setLayoutParams(matchParent);
        setContentView(flutterView);

        return flutterView;
    }

    @Override
    public FlutterNativeView createFlutterNativeView() {
        return null;
    }
}
