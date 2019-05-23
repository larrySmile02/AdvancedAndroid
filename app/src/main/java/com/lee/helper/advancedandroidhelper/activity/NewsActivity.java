package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.impl.RequestImp;
import com.lee.helper.advancedandroidhelper.model.IRequest;
import com.lee.helper.advancedandroidhelper.utils.NetWorkUtil;
import com.lee.helper.smartokhttp.HttpUtils;
import com.lee.helper.smartokhttp.R;

import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener
{
    private String api = "https://v.juhe.cn/toutiao/index";
    private String url = "https://v.juhe.cn/toutiao/index?type=top&key=483294d5e9b2202317817d0696b47a58";
    private final String API_KEY = "483294d5e9b2202317817d0696b47a58";
    private Button btnOKhttp;
    private Button btnUrlConnection;

    private Map<String ,String >params = new HashMap<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initViews();
        initData();


    }

    private void initData(){
        params.put("type","top");
        params.put("key",API_KEY);


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1000);
                e.onNext(2000);
                e.onNext(3000);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
//                mRxOperatorsText.append("accept : " + s +"\n");
//                Log.e(TAG, "accept : " + s +"\n" );
            }
        });

    }

    private void initViews(){
        btnOKhttp =  findViewById(R.id.btn_news);
        btnOKhttp.setOnClickListener(this);
        btnUrlConnection = findViewById(R.id.btn_news2);
        btnUrlConnection.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_news){
            HttpUtils.get(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("NEW","e = "+e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("NEW","response = "+response.toString());
                }
            });
        }else if(id == R.id.btn_news2){
            new Thread(() -> {
                    IRequest<String> request = new RequestImp(api,params,null);
                    NetWorkUtil.urlConnectionGet(request);
            }).start();

        }
    }
}
