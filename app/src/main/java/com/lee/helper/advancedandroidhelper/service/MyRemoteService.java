package com.lee.helper.advancedandroidhelper.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.lee.helper.advancedandroidhelper.IRemoteInterface;
import com.lee.helper.advancedandroidhelper.RemoteMsg;

import static com.lee.helper.advancedandroidhelper.constant.MyConstant.LOVER_NAME;


public class MyRemoteService extends Service {


    public static String TAG = MyRemoteService.class.getSimpleName();
    private String MY_LOVER = "young beautiful girl";
    private RemoteMsg localMsg;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate <=========>");
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        String name = MY_LOVER;
        Log.e(TAG,"onbindService :name = "+name+"<=========>");
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra(LOVER_NAME))){
            name = MY_LOVER+" : "+intent.getStringExtra(LOVER_NAME);
        }

        return new ConnectBinder(name);
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        super.unbindService(conn);
        Log.e(TAG,"unbindService <=========>");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy <=========>");
    }

    public  class ConnectBinder extends IRemoteInterface.Stub {

        private String lover ;
        public ConnectBinder(String myLover){
            this.lover = myLover;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public RemoteMsg getMsg() throws RemoteException {
           if (localMsg == null){
               localMsg = new RemoteMsg(2019,lover);
           }
            return localMsg;
        }

        @Override
        public void setMsg(RemoteMsg msg) throws RemoteException {
            if (msg != null)
                localMsg = msg;
        }
    }

}
