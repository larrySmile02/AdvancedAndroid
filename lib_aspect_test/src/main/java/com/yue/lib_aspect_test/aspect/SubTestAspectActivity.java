package com.yue.lib_aspect_test.aspect;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yue.lib.sample.TestAspectActivity;

public class SubTestAspectActivity extends TestAspectActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comeSubActivity();
        welecomeCome();
    }

    private void comeSubActivity(){
        Log.e(TAG,"comeSubActivity>>>>");
    }

    private void welecomeCome(){
        Log.e(TAG,"welcome ---->>>>");
    }
}
