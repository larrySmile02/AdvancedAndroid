package com.lee.helper.config;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.provider.Settings;
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
 * Modify by yue , 2019-2-13
 * 根据今日头条屏幕适配方案，
 * 经测试可以适配 huawei mate 7 , huawei nova , nasamsumg，OPPO， Meizu, google 手机，
 * 不能适配 huawei p20
 * 因为MypersonalActivity 对应xml文件中所有控件的高度总和为616dp，标准的屏幕高度为640dp,
 * 但是主流手机屏幕底部都会有一个虚拟导航栏，如果显示虚拟导航栏可用的屏幕高度就<640dp,
 * 导致的后果就是如果xml所有控件高度设置为640，在手机显示虚拟导航栏的时候就会显示不全；如果xml所有控件高度设置为<640，在手机隐藏虚拟导航栏的时候就会不满一屏；
 * 正在寻找两全其美的办法
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
        if(checkNavigationBarShow(activity)){
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
        Log.i("ConfigUI","screenHeight1 = "+screenHeight+"screenHeight2 = "+" targetDensity= "+targetDensity);
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
     * 判断虚拟导航栏是否显示
     * @param context 上下文对象
     * @return true(显示虚拟导航栏)，false(不显示或不支持虚拟导航栏)
     */
    public static boolean checkNavigationBarShow(@NonNull Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            //判断是否隐藏了底部虚拟导航
            int navigationBarIsMin = 0;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                navigationBarIsMin = Settings.System.getInt(context.getContentResolver(),
                        "navigationbar_is_min", 0);
            } else {
                navigationBarIsMin = Settings.Global.getInt(context.getContentResolver(),
                        "navigationbar_is_min", 0);
            }
            if ("1".equals(navBarOverride) || 1 == navigationBarIsMin) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {
        }
        return hasNavigationBar;
    }





    /**
     * 备用方法
     * 获取 底部虚拟导航的高度
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        if (checkNavigationBarShow(context)) {
            int totalHeight = getDpi(context);
            int contentHeight = getScreenHeight(context);
            return totalHeight - contentHeight;
        } else {
            return 0;
        }
    }





    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager)
                context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }
    //获取屏幕高度 不包含虚拟按键=
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
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



}
