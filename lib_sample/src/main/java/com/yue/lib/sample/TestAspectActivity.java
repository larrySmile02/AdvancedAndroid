package com.yue.lib.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yue.lib_sample.R;

public class TestAspectActivity extends AppCompatActivity {

    public static final String TAG = TestAspectActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_aspect);
    }



}
