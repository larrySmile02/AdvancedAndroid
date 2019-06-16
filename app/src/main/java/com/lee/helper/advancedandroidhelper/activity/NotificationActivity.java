package com.lee.helper.advancedandroidhelper.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.R;

/**
 * 1.测试Notification
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnNotif;
    private Notification notification;
    private NotificationManager manager;
    public static final int NOTIFY_REQUEST_CODED = 0x1;
    public static final int NOTIFY_ID = 0x2;
    public static final CharSequence CHANNEL_NAME  = "超越小妹妹";
    private int CHANNEL_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;
    private String CHANNEL_ID = "ID_CHAOYUE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();

    }

    private void initViews() {
        btnNotif = findViewById(R.id.btn_notify);
        btnNotif.setOnClickListener(this);
        Intent intent = new Intent(this,TestAnimatiorActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,NOTIFY_REQUEST_CODED,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            addChannel();
        }

        //这里以后做兼容，导入androidx
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).
                setSmallIcon(R.mipmap.ic_launcher_round).
                setContentTitle("标题:notifyDemo").
                setContentText("内容 ：测试notify").
                setContentIntent(pendingIntent).
                setChannelId(CHANNEL_ID);

        notification = builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addChannel(){
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,CHANNEL_IMPORTANCE);
        manager.createNotificationChannel(channel);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_notify:
                manager.notify(NOTIFY_ID,notification);
                break;
            default:
                break;
        }
    }
}
