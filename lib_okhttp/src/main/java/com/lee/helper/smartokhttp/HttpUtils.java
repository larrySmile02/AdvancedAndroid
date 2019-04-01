package com.lee.helper.smartokhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils
{
    public static void get(String url , Callback callback){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().
                url(url).
                get().
                build();
        client.newCall(request).enqueue(callback);
    }
}
