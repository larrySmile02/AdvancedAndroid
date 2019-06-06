package com.lee.helper.advancedandroidhelper.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import static com.lee.helper.advancedandroidhelper.constant.MyConstant.LOVER_NAME;


public class MyRemoteService extends Service {


    public static String TAG = MyRemoteService.class.getSimpleName();
    private String MY_LOVER = "Remote_girl";



    private RometThreadMsg remoteMsg;
    private RometThreadMsg.ConnectBinder connectBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        remoteMsg = new RometThreadMsg();
        connectBinder =  remoteMsg.getConnectBinder();

        Log.e(TAG,"onCreate <=========>");
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        String name = MY_LOVER;
        Log.e(TAG,"onbindService :name = "+name+"<=========>");
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra(LOVER_NAME))){
            name = MY_LOVER+" : "+intent.getStringExtra(LOVER_NAME);
            try {
                connectBinder.getMsg().setName(name);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        connectBinder.startChange();
        return connectBinder;
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

//    public  class ConnectBinder extends IRemoteInterface.Stub {
//
//        private String lover ;
//        private int index = ORIGIN_INDEX;
//        public ConnectBinder(String myLover){
//            this.lover = myLover;
//        }
//
//        @Override
//        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
//
//        }
//
//        @Override
//        public RemoteMsg getMsg() throws RemoteException {
//           if (localMsg == null){
//               localMsg = new RemoteMsg(index,lover);
//           }
//
//            return localMsg;
//        }
//
//        @Override
//        public void setMsg(RemoteMsg msg) throws RemoteException {
//            if (msg != null)
//                localMsg = msg;
//        }
//
//        //本来试图看看2个进程内IBinder对象的变化是否同步，还没有成功
//
//        public void startChange(){
//            if(executorService != null){
//                executorService.scheduleAtFixedRate(new Runnable() {
//                    @Override
//                    public void run() {
//                        index++;
//                        localMsg.setId(index);
//                        Log.e("TEST_AIDL","remote_ "+localMsg.toString());
//                    }
//                }, 0,1000 ,TimeUnit.MILLISECONDS);
//            }
//        }
//    }

}
