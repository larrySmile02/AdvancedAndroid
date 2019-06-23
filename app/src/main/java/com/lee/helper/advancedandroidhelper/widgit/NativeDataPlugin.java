package com.lee.helper.advancedandroidhelper.widgit;

import android.util.Log;

import com.lee.helper.toast.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

public class NativeDataPlugin implements MethodChannel.MethodCallHandler {

    public static final String NEWS_DATA = "news_data";
    public static String CHANNEL = "com.mmd.flutterapp/plugin";

    static MethodChannel channel;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        channel = new MethodChannel(registrar.messenger(),CHANNEL);
        NativeDataPlugin plugin = new NativeDataPlugin();
        channel.setMethodCallHandler(plugin);
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

        if(methodCall.method.equals(NEWS_DATA)){
            Log.e("NativeData","echo<--");
            Map<String,String> resutMap = new HashMap<>();
            resutMap.put("result","I want u!!!");
            result.success(resutMap);
            ToastUtils.show("NEWS_DATA,Success!!!");
        }
    }
}
