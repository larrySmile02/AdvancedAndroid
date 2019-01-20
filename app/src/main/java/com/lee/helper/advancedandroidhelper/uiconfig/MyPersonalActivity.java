package com.lee.helper.advancedandroidhelper.uiconfig;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.config.ConfigUIActivity;


public class MyPersonalActivity extends ConfigUIActivity
{

    @Override
    public int getConfigBase() {
        return ConfigUIActivity.CONFIG_HEIGH_BASE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalcenter_fragment_native);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}
