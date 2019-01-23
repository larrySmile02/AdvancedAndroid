package com.lee.helper.advancedandroidhelper.uiconfig;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.config.ConfigUIActivity;

public class GuideActivity extends ConfigUIActivity implements View.OnClickListener
{

    private Button btnRiger;

    @Override
    public int getConfigBase() {
        return ConfigUIActivity.CONFIG_WITH_BASE;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_guide);
        btnRiger = findViewById(R.id.btn_register);
        btnRiger.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_register){
            startActivity(new Intent(GuideActivity.this,MyPersonalActivity.class));
        }
    }
}
