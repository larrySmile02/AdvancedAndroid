package com.lee.helper.advancedandroidhelper.activity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.adapter.RecNewsAdapter;
import com.lee.helper.advancedandroidhelper.bean.NewsItem;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class TestAsyncTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rec;
    private Button btn;
    private Button testAd;
    private RecNewsAdapter adapter;
    private List<NewsItem> items = new ArrayList<>();
    private Gson gson;
    AudioManager audioManager;
    private FrameLayout fltBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn);
        rec = findViewById(R.id.rec);
        btn = findViewById(R.id.btn_async);
        testAd = findViewById(R.id.btn_audio);
        testAd.setOnClickListener(this);
        btn.setOnClickListener(this);
        adapter = new RecNewsAdapter(this,items);
        gson = new Gson();
        fltBack = findViewById(R.id.flt_back);
        fltBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LinkedList linkedList = new LinkedList();
        linkedList.iterator();



    }

    @Override
    protected void onResume() {
        super.onResume();
        initAudiao();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_async){

            testRxjava();

        } else if (id == R.id.btn_audio) {

            restAudiao();
        }
    }

    private void initAudiao(){
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        audioManager.setSpeakerphoneOn(false);
        Toast.makeText(this,"initAudiao",Toast.LENGTH_SHORT).show();

    }

    private void restAudiao(){
        audioManager.setMode(AudioManager.MODE_NORMAL);
    }


    private void testRxjava(){
        Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> emitter)  {

                emitter.onNext(10);

            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer)  {
                return "hug limiao => "+integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Toast.makeText(TestAsyncTaskActivity.this,s,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }





}
