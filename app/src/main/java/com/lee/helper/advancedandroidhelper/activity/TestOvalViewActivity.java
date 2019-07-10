package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.custom.ConfigProgressView;

public class TestOvalViewActivity extends AppCompatActivity {

    private ConfigProgressView processView;
    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_oval);
        processView = findViewById(R.id.process_view);
        btn = findViewById(R.id.btn_change);
        btn.setOnClickListener(v -> { processView.setProcessIndex(3);});
    }
}
