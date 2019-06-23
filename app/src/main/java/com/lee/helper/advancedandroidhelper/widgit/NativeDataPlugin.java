package com.lee.helper.advancedandroidhelper.widgit;

import com.lee.helper.toast.ToastUtils;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.StandardMessageCodec;

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
            ToastUtils.show("NEWS_DATA,Success!!!");
        }
    }
}
