package com.lee.helper.advancedandroidhelper.widgit;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.lee.helper.advancedandroidhelper.R;

public class WidgetUtil {

    public static RemoteViews getRemoteViews(Context mContext){
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.advance_widgit);
        Intent intent = new Intent(mContext,AdvanceAppWidget.class);
        intent.setAction(AdvanceAppWidget.ACTION_CLICK);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.img_ycy,pendingIntent);
        return remoteViews;
    }
}
