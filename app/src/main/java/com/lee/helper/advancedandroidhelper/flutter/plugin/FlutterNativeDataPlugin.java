package com.lee.helper.advancedandroidhelper.flutter.plugin;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.lee.helper.advancedandroidhelper.activity.NativeGetDataActivity;
import com.lee.helper.advancedandroidhelper.flutter.util.FlutterNetWokUtil;
import com.lee.helper.advancedandroidhelper.flutter.util.NewsApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FlutterNativeDataPlugin implements MethodChannel.MethodCallHandler {

    public static final String NEWS_DATA = "news_data";
    public static final String CHAO_YUE = "chao_yue";
    public static String CHANNEL = "com.mmd.flutterapp/plugin";

    static MethodChannel channel;
    private Activity mActivity;
    Map<String,String> resutMap = new HashMap<>();

    private void setContext(Activity mActivity){
        this.mActivity = mActivity;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        channel = new MethodChannel(registrar.messenger(),CHANNEL);
        FlutterNativeDataPlugin plugin = new FlutterNativeDataPlugin();
        channel.setMethodCallHandler(plugin);
        plugin.setContext(registrar.activity());
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {

        if(methodCall.method.equals(NEWS_DATA)){

            FlutterNetWokUtil.get(NewsApi.NewsApi, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    resutMap.clear();
                    result.error("error","e.toString()",null);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    resutMap.clear();
                    resutMap.put("result",response.body().string());
                    result.success(resutMap);
                }
            });


        }else if(methodCall.method.equals(CHAO_YUE)){
            Log.e("SIMPLE_DATA" , "argue = "+methodCall.argument("data").toString());
            if(mActivity != null){
//                Intent intent = new Intent(mActivity, NativeGetDataActivity.class);
//                mActivity.startActivity(intent);
//                resutMap.clear();
                resutMap.put("result","get_simple_data_success");
                result.success(resutMap);
            }

        }
    }
}
