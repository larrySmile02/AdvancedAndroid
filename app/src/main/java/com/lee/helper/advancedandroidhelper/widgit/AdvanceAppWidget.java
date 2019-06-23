package com.lee.helper.advancedandroidhelper.widgit;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class AdvanceAppWidget extends AppWidgetProvider {

    public static final String ACTION_CLICK = "com.advance.APPWIDGET_CLICK";

    /**AppWidget"可用"后首先调用，仅一次*/
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.e("ADVANCE_WIDGIT","onEnabled <==>");
    }

    /**AppWidget有什么动静都会调用，推测其他的方法调用都是onReceive分发的*/
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("ADVANCE_WIDGIT","onReceive <==> action = " + intent.getAction());
        super.onReceive(context, intent);


    }

    /**AppWidget"可用"后会调用，仅一次，RometeViews初始化会放在这里*/
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int widgetId : appWidgetIds){
            RemoteViews remoteViews =  WidgetUtil.getRemoteViews(context);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);
        }

        if(appWidgetIds != null && appWidgetIds.length > 0){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i< appWidgetIds.length; i++){
                builder.append(appWidgetIds[i]);
                builder.append("-->");
            }
            Log.e("ADVANCE_WIDGIT","onUpdate <==>"+builder.toString());
        }

    }

    /**appWidget从桌面移除后先调用，仅一次*/
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.e("ADVANCE_WIDGIT","onDelete <==>");
    }

    /**appWidget从桌面移除后会调用，仅一次*/
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.e("ADVANCE_WIDGIT","onDisable <==>");
    }
}
