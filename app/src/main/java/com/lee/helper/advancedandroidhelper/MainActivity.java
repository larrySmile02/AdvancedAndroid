package com.lee.helper.advancedandroidhelper;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lee.helper.advancedandroidhelper.activity.NativeGetDataActivity;
import com.lee.helper.advancedandroidhelper.activity.NavigationViewActivity;
import com.lee.helper.advancedandroidhelper.activity.NewsActivity;
import com.lee.helper.advancedandroidhelper.activity.NotificationActivity;
import com.lee.helper.advancedandroidhelper.activity.ScrollViewActivity;
import com.lee.helper.advancedandroidhelper.activity.TestAnimatiorActivity;
import com.lee.helper.advancedandroidhelper.activity.TestAsyncTaskActivity;
import com.lee.helper.advancedandroidhelper.activity.TestCustomWindowActivity;
import com.lee.helper.advancedandroidhelper.activity.TestFlutterViewActivity;
import com.lee.helper.advancedandroidhelper.activity.TestFragmentActivity;
import com.lee.helper.advancedandroidhelper.activity.TestFragmentPagerActivity;
import com.lee.helper.advancedandroidhelper.activity.TestGenerateImageActivity;
import com.lee.helper.advancedandroidhelper.activity.TestOvalViewActivity;
import com.lee.helper.advancedandroidhelper.activity.TestSharedPreferenceActivity;
import com.lee.helper.advancedandroidhelper.activity.ViewEventActivity;
import com.lee.helper.advancedandroidhelper.adapter.RecMainAdapter;
import com.lee.helper.advancedandroidhelper.constant.MyConstant;
import com.lee.helper.advancedandroidhelper.event.ServiceEvent;
import com.lee.helper.advancedandroidhelper.model.IMainActivity;
import com.lee.helper.advancedandroidhelper.service.MyJobService;
import com.lee.helper.advancedandroidhelper.service.MyRemoteService;
import com.lee.helper.advancedandroidhelper.service.MyTestService;
import com.lee.helper.advancedandroidhelper.service.RometThreadMsg;
import com.lee.helper.advancedandroidhelper.utils.CommonUtil;
import com.lee.helper.advancedandroidhelper.utils.GenerateImageLogic;
import com.lee.helper.config.ConfigUIActivity;
import com.lee.helper.config.GlobelActivityConfig;
import com.lee.helper.recycler.widget.SimpleDividerItemDoceration;
import com.lee.helper.toast.ToastUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class MainActivity extends ConfigUIActivity implements IMainActivity {

    /**
     * 默认分割线高度
     */
    private final int DEFAUT_DIVIDER_HEIGHT = 8;
    private RecyclerView recView;
    private RecMainAdapter adapter;
    private final int ROMENT_MSG = 0x1;
    private String[] items = new String[]{"Config Demo", "Toast Demo", "RecyclerView Demo", "start service", "remote", "MsgRemote"
            , "JobIntentService", "slideView", "flutterMain", "Animator", "RxPermission ", "News", "ViewEvent", "Notification",
            "NavigationView","ovalView","ViewPagerAdapter","TestFragment","CheckSp","FrescoDemo","CustomWindow","Test Async"};
    private IRemoteInterface mIRemoteInterface;
    private ImageView ivTemp;
    private RometThreadMsg rometThreadMsg;
    private ScheduledExecutorService executorService;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ROMENT_MSG:
                    RemoteMsg msg1 = (RemoteMsg) msg.obj;
                    executorService.scheduleAtFixedRate(() -> Log.e("TEST_AIDL", msg1.toString()), 0, 1500, TimeUnit.MILLISECONDS);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getConfigBase() {
        return CONFIG_ORIGIN;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initExecutor();
        initViews();
        EventBus.getDefault().register(this);
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap = GenerateImageLogic.getIns().shotRecyclerView(recView);
//                Log.e("CommonUtil", "bitmap2 = " + bitmap.toString());
//                if(bitmap != null){
//                    startActivity(new Intent(MainActivity.this, TestGenerateImageActivity.class));
//                }
//            }
//        }, 2000);

    }

    @Override
    protected void onResume() {
        super.onResume();
        GlobelActivityConfig.fitBasedHeight(this);
    }

    private void initViews() {
        recView = findViewById(R.id.rec);
        List<String> itemList = Arrays.asList(items);
        adapter = new RecMainAdapter(this, itemList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        SimpleDividerItemDoceration decoration = new SimpleDividerItemDoceration(this, DEFAUT_DIVIDER_HEIGHT, R.color.advance_transparent, true);
        recView.setLayoutManager(layoutManager);
        recView.addItemDecoration(decoration);
        recView.setAdapter(adapter);
        ivTemp = findViewById(R.id.iv_temp);


    }

    private void initExecutor() {
        executorService = new ScheduledThreadPoolExecutor(3);

    }

    private void startMyService() {
        Intent intent = new Intent(this, MyTestService.class);
        intent.putExtra(MyConstant.MARK_TO_SERVICE, "COM FROM INTENT service");
        startService(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ServiceEvent event) {
        ToastUtils.show("Get from Service");
    }


    @Override
    public void onStartMyService() {
        startMyService();
    }

    //这个方法搞的这么复杂是想模仿ActivityThread#ApplicationThread，测试IBinder实例在2个进程内是否同步.
    //之所以要测试，是因为我不明白system_server进程是如何操控app进程内的逻辑的。
    //难度比较大，还没有测试成功。
    @Override
    public void onStartRemoteService() {
        rometThreadMsg = new RometThreadMsg();
        Intent remoteIntent = new Intent(this, MyRemoteService.class);
//        remoteIntent.putExtra(MyConstant.REMOTE_MSG, rometThreadMsg.getConnectBinder());
        bindService(remoteIntent, conn, BIND_AUTO_CREATE);

    }

    @Override
    public void onSetRemoteMsg() {

        try {
            RemoteMsg setMsg = new RemoteMsg(666, "佳诺");
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
        JonIntent.putExtra(MyConstant.SEXY_GIRL, "吉泽明步");
        MyJobService.equeue(this, JonIntent);

    }

    @Override
    public void gotoSlideView() {
        Intent slideViewIntent = new Intent(this, ScrollViewActivity.class);
        startActivity(slideViewIntent);
    }

    @Override
    public void gotoFlutterMain() {

        Intent flutterIntent = new Intent(this, NativeGetDataActivity.class);
        startActivity(flutterIntent);
    }

    @Override
    public void gotoAnimator() {
        Intent intent = new Intent(this, TestAnimatiorActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoNews() {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoEventTest() {
        Intent intentEvent = new Intent(this, ViewEventActivity.class);
        startActivity(intentEvent);
    }

    @Override
    public void gotoNotifyCation() {
        Intent notifyIntent = new Intent(this, NotificationActivity.class);
        startActivity(notifyIntent);
    }

    @Override
    public void getStoragePermission() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.
                request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).
                subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) {
                        Log.e("rxPermission","granted ==");
                    }
                });
    }

    @Override
    public void gotoNavigationViewActivity() {
        startActivity(new Intent(this, NavigationViewActivity.class));
    }

    @Override
    public void gotoOvalView() {
        startActivity(new Intent(this, TestOvalViewActivity.class));
    }

    @Override
    public void gotoViewPager() {
        startActivity(new Intent(this, TestFragmentPagerActivity.class));
    }

    @Override
    public void gotoTestFragment() {
        startActivity(new Intent(this, TestFragmentActivity.class));
    }

    @Override
    public void gotoTestSp() {
        startActivity(new Intent(this, TestSharedPreferenceActivity.class));
    }

    @Override
    public void gotoFrescoDemo() {
//        startActivity(new Intent(this, TestFrescoActivity.class));
    }

    @Override
    public void gotoCustomWindow() {
        startActivity(new Intent(this, TestCustomWindowActivity.class));
    }

    @Override
    public void gotoTestAsync() {
        startActivity(new Intent(this, TestAsyncTaskActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        unbindService(conn);
        executorService.shutdown();
    }


    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //如果是远程Service，返回的IBinder service是BinderProxy对象
            //如果非远程Service，返回的是IBinder对象，无需AIDL就可直接通信
            RemoteMsg msg = null;
            if (service != null) {

                try {
                    mIRemoteInterface = IRemoteInterface.Stub.asInterface(service);
                    msg = mIRemoteInterface.getMsg();
                    if (msg != null) ToastUtils.show(msg.toString());
                    Message rometeMsg = mHandler.obtainMessage();
                    rometeMsg.what = ROMENT_MSG;
                    rometeMsg.obj = msg;
                    mHandler.sendMessage(rometeMsg);
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
