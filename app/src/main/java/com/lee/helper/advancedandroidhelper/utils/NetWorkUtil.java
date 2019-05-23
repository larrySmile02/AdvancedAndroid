package com.lee.helper.advancedandroidhelper.utils;

import android.text.TextUtils;
import android.util.Log;
import com.lee.helper.advancedandroidhelper.model.IRequest;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class NetWorkUtil {

    public static final int TIME_OUT = 30*1000;
    public static final String GET="get";

    public static void urlConnectionGet(IRequest request)  {

        HttpsURLConnection connection = null;
        String api = request.getApi();
        api+= ("?"+ buildGetUrl(request.getParams()));
        Log.e("HTTPS-GetUrl",api);
        try {
            if(!TextUtils.isEmpty(api)){
                URL Url = new URL(api);
                connection = (HttpsURLConnection) Url.openConnection();
                urlConnectionSetting(connection,GET,request.getHeader());
                int responseCode = connection.getResponseCode();
                if(responseCode  == HttpsURLConnection.HTTP_OK){
                    Log.e("HTTPS","ok");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void urlConnectionSetting(HttpsURLConnection connection, String method,Map<String ,String> headerMap){
        connection.setConnectTimeout(TIME_OUT);
        connection.setReadTimeout(TIME_OUT);

        try {
            connection.setRequestMethod(method);
            if(TextUtils.equals(method,GET)){
                connection.setRequestProperty("Accept-Encoding", "gzip");
                if(headerMap != null && headerMap.size() > 0){
                   Iterator iterator = headerMap.keySet().iterator();
                   while (iterator.hasNext()){
                       String key = (String) iterator.next();
                       String value = headerMap.get(key);
                       connection.setRequestProperty(key,value);
                   }
                }

            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        }

    }


    public static String buildGetUrl(Map<String,String> map){
        String urlGet = "";
        if(map != null && map.size() >0){
            StringBuilder builder = new StringBuilder();
            Iterator itr = map.keySet().iterator();

            while (itr.hasNext()){
                String key = (String) itr.next();
                builder.append(key +"="+map.get(key)+"&");
            }
            urlGet = builder.toString();
            if(urlGet.endsWith("&")) urlGet = urlGet.substring(0,urlGet.lastIndexOf("&")-1);

        }
        return urlGet;
    }

}
