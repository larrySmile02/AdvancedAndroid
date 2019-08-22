package com.lee.helper.advancedandroidhelper.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.toast.ToastUtils;

public class TestSharedPreferenceActivity extends AppCompatActivity {

    private Button btnSet;
    private Button btn;
    private final String CHECK_SP = "CHECK_SP";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_sp);
        btn = findViewById(R.id.btn_check_sp);
        btnSet = findViewById(R.id.btn_set_sp);
        btnSet.setOnClickListener(v -> {
            setSP();
        });
        btn.setOnClickListener(v -> {
            getSp();
        });

    }

    private void setSP(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CHECK_SP,"I love sexy girl");
        editor.commit();
    }

    private void getSp(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String s = sp.getString(CHECK_SP,"Nothing");
        ToastUtils.show(s);
    }
}
