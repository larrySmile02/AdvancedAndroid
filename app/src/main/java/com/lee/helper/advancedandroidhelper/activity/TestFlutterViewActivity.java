package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lee.helper.advancedandroidhelper.R;

import io.flutter.facade.Flutter;

public class TestFlutterViewActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_test);
        findViewById(R.id.iv_fab).setOnClickListener(v -> {
            View flutterView = Flutter.createView(
                    TestFlutterViewActivity.this,
                    getLifecycle(),
                    "route1"
            );

            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            addContentView(flutterView, layout);
        });

    }
}
