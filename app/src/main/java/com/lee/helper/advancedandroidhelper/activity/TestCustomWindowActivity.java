package com.lee.helper.advancedandroidhelper.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.utils.CommonUtil;

public class TestCustomWindowActivity extends AppCompatActivity {
    private final int REQUEST_CODE = 0x1;
    private Button btnShow;
    private WindowManager wm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_json);
        btnShow = findViewById(R.id.iv_json);
        wm = getWindowManager();
        btnShow.setOnClickListener(v -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(TestCustomWindowActivity.this)){
                getOverlayPermission();
            }else {
                showCustomWindow();
            }
        });


    }

    private void getOverlayPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void showCustomWindow(){
        Button mFloatBtn = new Button(this);
        mFloatBtn.setBackground(getResources().getDrawable(R.drawable.window_custom_bg));
        mFloatBtn.setText("梦琪");
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(CommonUtil.dp2px(this,150),
                CommonUtil.dp2px(this,50),WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,0, PixelFormat.TRANSPARENT);
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE ;
        params.windowAnimations = android.R.style.Animation_Dialog;
        params.gravity = Gravity.CENTER_VERTICAL|Gravity.LEFT;
        wm.addView(mFloatBtn,params);
    }



}
