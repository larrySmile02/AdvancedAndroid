package com.lee.helper.advancedandroidhelper;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.lee.helper.advancedandroidhelper.adapter.RecMainAdapter;
import com.lee.helper.advancedandroidhelper.constant.MyConstant;
import com.lee.helper.advancedandroidhelper.event.ServiceEvent;
import com.lee.helper.advancedandroidhelper.model.IMainActivity;
import com.lee.helper.advancedandroidhelper.service.MyJobService;
import com.lee.helper.advancedandroidhelper.service.MyRemoteService;
import com.lee.helper.advancedandroidhelper.service.MyTestService;
import com.lee.helper.config.ConfigUIActivity;
import com.lee.helper.recycler.widget.SimpleDividerItemDoceration;
import com.lee.helper.toast.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ConfigUIActivity implements IMainActivity {

    /** 默认分割线高度*/
    private final int DEFAUT_DIVIDER_HEIGHT = 1;
    private RecyclerView recView;
    private RecMainAdapter adapter;
    private String [] items = new String[]{"Config Demo","Toast Demo","RecyclerView Demo","start service","remote","MsgRemote"
    ,"JobIntentService"};
    private IRemoteInterface mIRemoteInterface;

    @Override
    public int getConfigBase() {
        return CONFIG_ORIGIN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        EventBus.getDefault().register(this);
    }

    private void initViews(){
        recView = findViewById(R.id.rec);
        List<String> itemList = Arrays.asList(items);
        adapter = new RecMainAdapter(this,itemList,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SimpleDividerItemDoceration decoration = new SimpleDividerItemDoceration(this,DEFAUT_DIVIDER_HEIGHT,R.color.advance_bfbfbf,true);
        recView.setLayoutManager(layoutManager);
        recView.addItemDecoration(decoration);
        recView.setAdapter(adapter);

    }

    private void  startMyService(){
        Intent intent = new Intent(this, MyTestService.class);
        intent.putExtra(MyConstant.MARK_TO_SERVICE,"COM FROM INTENT service");
        startService(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ServiceEvent event){
        ToastUtils.show("Get from Service");
    }


    @Override
    public void onStartMyService() {
        startMyService();
    }

    @Override
    public void onStartRemoteService() {
        Intent remoteIntent = new Intent(this,MyRemoteService.class);
        remoteIntent.putExtra(MyConstant.LOVER_NAME,"诗婷");
        bindService(remoteIntent,conn,BIND_AUTO_CREATE);

    }

    @Override
    public void onSetRemoteMsg() {

        try {
            RemoteMsg setMsg = new RemoteMsg(666,"佳诺");
            mIRemoteInterface.setMsg(setMsg);
            RemoteMsg getMsg = mIRemoteInterface.getMsg();
            ToastUtils.show(getMsg.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStartJobIntentService() {
        Intent JonIntent = new Intent(this, MyJobService.class);
        JonIntent.putExtra(MyConstant.SEXY_GIRL,"吉泽明步");
        MyJobService.equeue(this,JonIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //如果是远程Service，返回的IBinder service是BinderProxy对象
            //如果非远程Service，返回的是IBinder对象，无需AIDL就可直接通信
            RemoteMsg msg = null;
            if(service != null){

                try {
                    mIRemoteInterface = IRemoteInterface.Stub.asInterface(service);
                    msg = mIRemoteInterface.getMsg();
                    if(msg != null)ToastUtils.show(msg.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ToastUtils.show("what a fuck");
        }
    };
}
