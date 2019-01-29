package com.lee.helper.advancedandroidhelper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lee.helper.advancedandroidhelper.uiconfig.GuideActivity;
import com.lee.helper.advancedandroidhelper.uiconfig.MyPersonalActivity;
import com.lee.helper.toast.ToastUtils;

public class MainActivity extends AppCompatActivity {

    private Button btnConfig;
    private Button btnToast;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
        btnConfig = findViewById(R.id.btn_config);
        btnConfig.setOnClickListener(v -> mActivity.startActivity(new Intent(mActivity, GuideActivity.class)));
        btnToast = findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(v -> {
            ToastUtils.preToast(MainActivity.this);
            ToastUtils.show("点一下我试试");
        });

    }

//    @Override
//    public void onClick(View v) {
//        int id = v.getId();
//        if(id == R.id.btn_config){
//            startActivity(new Intent(MainActivity.this, GuideActivity.class));
//        }else if(id == R.id.btn_toast){
//            ToastUtils.preToast(MainActivity.this);
//            ToastUtils.show("点一下我试试");
//        }
//
//    }
}
