package com.lee.helper.config;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class ConfigUIActivity extends AppCompatActivity
{
    public static final int CONFIG_WITH_BASE = 0x1; //以宽为维度适配
    public static final int CONFIG_HEIGH_BASE = 0x2; //以高为维度适配
    public static final int CONFIG_ORIGIN = 0x3; //不做适配

    public abstract int getConfigBase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getConfigBase() == CONFIG_WITH_BASE){
            GlobelPersonUIconfig.setCustomWithDensity(getApplication(),this);
        }else if(getConfigBase() == CONFIG_HEIGH_BASE){
            GlobelPersonUIconfig.setCustomHeightDensity(getApplication(),this);
        }else if(getConfigBase() == CONFIG_ORIGIN){
            GlobelPersonUIconfig.setOrigDensity(getApplication(),this);
        }

    }
}
