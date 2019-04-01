package com.lee.helper.advancedandroidhelper.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.lee.helper.advancedandroidhelper.constant.MyConstant;
import com.lee.helper.advancedandroidhelper.event.ServiceEvent;

import org.greenrobot.eventbus.EventBus;


public class MyTestService extends IntentService {

    private String TAG = MyTestService.class.getSimpleName();
    private int TOAST_MARK = 0x1;
    private int TOAST_INTENT = 0X2;
    private int NOTITY_ACTIVITY = 0x3;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == TOAST_MARK){
                Toast.makeText(MyTestService.this,"I'm in service",Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(TOAST_MARK,10000);
            }else if(msg.what == TOAST_INTENT){
                String objString = "emmmmm"+(String) msg.obj;
                Toast.makeText(MyTestService.this,objString,Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(TOAST_INTENT,10000);
            }else if(msg.what == NOTITY_ACTIVITY){
                EventBus.getDefault().post(new ServiceEvent());
            }

        }
    };


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public MyTestService() {
        super("MyTestService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);
        Log.e(TAG,"onStartCommand");
//        mHandler.sendEmptyMessageDelayed(TOAST_MARK,10000);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        Log.e(TAG,"onHandleIntent");
        if (intent != null){
            String obj = intent.getStringExtra(MyConstant.MARK_TO_SERVICE);
            Message msg = mHandler.obtainMessage();
            msg.what = NOTITY_ACTIVITY;
            msg.obj = obj;
            mHandler.sendMessage(msg);
        }
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        return super.bindService(service, conn, flags);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }

}
