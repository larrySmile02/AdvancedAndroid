package com.lee.helper.config;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

public class GlobelActivityConfig {

    public static void fitBasedHeight(AppCompatActivity mActivity){

        AppCompatDelegate delegate = mActivity.getDelegate();
        ViewGroup viewGroup = null;

        if(delegate != null){
            Log.e("CONFIG_A","1 SUC : delegate not null");
            try {
                Class clz =delegate.getClass();
                Field field = clz.getDeclaredField("mSubDecor");
                field.setAccessible(true);
                if(field != null){
                    Log.e("CONFIG_A","2 SUC : Field not null");
                    viewGroup = (ViewGroup) field.get(delegate);
                    if (viewGroup != null){
                        int height = viewGroup.getHeight();
                        Log.e("CONFIG_A","3 SUC : viewGroup not null , height = "+height);
                    }
                }
            }catch (Exception e){
                Log.e("CONFIG_A","e = "+e.toString());
            }
        }

    }
}
