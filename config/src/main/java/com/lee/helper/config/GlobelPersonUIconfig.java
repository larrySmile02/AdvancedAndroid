package com.lee.helper.config;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.Method;


/**
 * 根据今日头条屏幕适配方案
 * */
public class GlobelPersonUIconfig {

    private static float sNonCompatDesity;
    private static float sNonCompatScaleDesity;

    public static void setCustomHeightDensity(@NonNull final Application application , @NonNull Activity activity){

        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();
        if(sNonCompatDesity == 0){
            sNonCompatDesity = appDisplayMetric.density;
            sNonCompatScaleDesity = appDisplayMetric.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale > 0){
                        sNonCompatScaleDesity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        //Android 4.2以上华为手机的虚拟导航可能会导致 appDisplayMetric.heightPixels 小于可显示的屏幕高度，所以就换一种方法获取
        float screenHeight = -1f;
        if(checkHasNavigationBar(activity)){
                screenHeight = 1f *appDisplayMetric.heightPixels;
        }else {
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1){
                screenHeight = 1f *getRealScreenHeight(activity);
            }else {
                screenHeight = 1f *appDisplayMetric.heightPixels;
            }
        }
        final float targetDensity = screenHeight / 640;
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDesity / sNonCompatDesity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        Log.i("ConfigUI","screenHeight = "+screenHeight+" targetDensity= "+targetDensity);
        activityDisplayMetric.density = targetDensity;
        activityDisplayMetric.scaledDensity = targetScaleDensity;
        activityDisplayMetric.densityDpi = targetDensityDpi;

    }

    public static void setCustomWithDensity(@NonNull final Application application , @NonNull Activity activity){

        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();
        if(sNonCompatDesity == 0){
            sNonCompatDesity = appDisplayMetric.density;
            sNonCompatScaleDesity = appDisplayMetric.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if(newConfig != null && newConfig.fontScale > 0){
                        sNonCompatScaleDesity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = 1f * appDisplayMetric.widthPixels / 360;
        Log.i("ConfigUI","origin_widthPixels = "+appDisplayMetric.widthPixels+" targetDensity= "+targetDensity);
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDesity / sNonCompatDesity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        activityDisplayMetric.density = targetDensity;
        activityDisplayMetric.scaledDensity = targetScaleDensity;
        activityDisplayMetric.densityDpi = targetDensityDpi;

    }



    public static void setOrigDensity(@NonNull final Application application , @NonNull Activity activity){
        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();


        activityDisplayMetric.density = appDisplayMetric.density;
        activityDisplayMetric.scaledDensity = appDisplayMetric.scaledDensity;
        activityDisplayMetric.densityDpi = appDisplayMetric.densityDpi;
    }


    /**
     * 获取屏幕的宽，
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕的高度
     *
     * @param context
     * @return
     */
    public static int getRealScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }


    /**
     * 获取虚拟键的高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        int navigationBarHeight = -1;
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height","dimen", "android");
        if (resourceId > 0) {
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }

    /**
     * 判断是否有虚拟导航栏
     *
     * @param activity
     * @return
     */
    public static boolean checkHasNavigationBar(Activity activity) {
        boolean hasNavigationBar = false;
        Resources rs = activity.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;


    }



}
