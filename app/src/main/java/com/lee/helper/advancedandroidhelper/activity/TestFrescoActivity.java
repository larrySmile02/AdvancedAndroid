//package com.lee.helper.advancedandroidhelper.activity;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.lee.helper.advancedandroidhelper.R;
//
//public class TestFrescoActivity extends AppCompatActivity {
//
//    private SimpleDraweeView sdv;
//    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565374493778&di=310f58196ec3d965d3b99c8dfc237919&imgtype=0&src=http%3A%2F%2Fg4.hexunimg.cn%2F2015-12-05%2F181009525.jpg";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fresco);
//
//        sdv = findViewById(R.id.sdv);
//        Uri uri = Uri.parse(url);
//        sdv.setImageURI(uri);
//
//
//    }
//}
