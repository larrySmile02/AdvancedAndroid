package com.lee.helper.advancedandroidhelper.service;


import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import com.lee.helper.advancedandroidhelper.IRemoteInterface;
import com.lee.helper.advancedandroidhelper.RemoteMsg;

import java.io.Serializable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RometThreadMsg {
    private static final int ORIGIN_INDEX = 2019;
    private final int RENEW_MSG = 0x2;
    private final String DEFAUT_NAME = "APP_妹纸";

    private RemoteMsg localMsg;
    private ScheduledExecutorService executorService;
    private ConnectBinder connectBinder;
    private Handler H = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RENEW_MSG:
                    localMsg = (RemoteMsg) msg.obj;
                    break;
                default:
                    break;
            }
        }
    };

    public RometThreadMsg() {
        executorService = new ScheduledThreadPoolExecutor(2);
        connectBinder = new ConnectBinder(DEFAUT_NAME);
    }

    public ConnectBinder getConnectBinder(){
        return connectBinder;
    }

    public class ConnectBinder extends IRemoteInterface.Stub {

        private String lover;
        private int index = ORIGIN_INDEX;

        public ConnectBinder(String myLover) {
            this.lover = myLover;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public RemoteMsg getMsg() throws RemoteException {
            if (localMsg == null) {
                localMsg = new RemoteMsg(index, lover);
            }

            return localMsg;
        }

        @Override
        public void setMsg(RemoteMsg msg) throws RemoteException {
            if (msg != null)
                localMsg = msg;
        }

        //本来试图看看2个进程内IBinder对象的变化是否同步，还没有成功

        public void startChange() {
            if (executorService != null) {
                executorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        index++;
                        localMsg.setId(index);
                        Log.e("TEST_AIDL", "remote_ " + localMsg.toString());
                        Message msg2 = H.obtainMessage();
                        msg2.what = RENEW_MSG;
                        msg2.obj = localMsg;
                    }
                }, 0, 1000, TimeUnit.MILLISECONDS);
            }
        }
    }
}
