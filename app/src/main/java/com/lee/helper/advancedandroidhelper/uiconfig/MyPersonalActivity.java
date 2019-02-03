package com.lee.helper.advancedandroidhelper.uiconfig;

import android.os.Bundle;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.config.ConfigUIActivity;
import com.lee.helper.smartokhttp.HttpUtils;
import com.lee.helper.toast.ToastUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Response;


public class MyPersonalActivity extends ConfigUIActivity {

    private static final String GRID_API_UK = "https://api.backendless.com/DBB73A3F-4B87-5C72-FF30-BCE8810F8700/3F81588C-4200-BA2F-FF66-2FDE2DF63400/services/GridSettingsUK/GetSettings";
    private static final String BAIDU = "https://www.baidu.com/";

    @Override
    public int getConfigBase() {
        return ConfigUIActivity.CONFIG_HEIGH_BASE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalcenter_fragment_native);
        initData();

    }

    private void initData() {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(final ObservableEmitter<String> emitter) {

                HttpUtils.get(GRID_API_UK, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        emitter.onNext(e.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            String body = response.body().string();
                            emitter.onNext(body);
                        }
                    }


                });


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String sb) {
                        ToastUtils.show(sb);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
