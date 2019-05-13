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
 * 经测试可以适配 huawei mate 7 , huawei p20，huawei nova , nasamsumg，OPPO， Meizu, google 手机，
 */
public class GlobelPersonUIconfig {

    private static float sNonCompatDesity;
    private static float sNonCompatScaleDesity;

    public static void setCustomHeightDensity(@NonNull final Application application, @NonNull Activity activity) {

        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();
        if (sNonCompatDesity == 0) {
            sNonCompatDesity = appDisplayMetric.density;
            sNonCompatScaleDesity = appDisplayMetric.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaleDesity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        float validHeight = 1f * appDisplayMetric.heightPixels; //不包括状态栏和导航栏的可用高度
        final float targetDensity = validHeight / 640;
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDesity / sNonCompatDesity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        activityDisplayMetric.density = targetDensity;
        activityDisplayMetric.scaledDensity = targetScaleDensity;
        activityDisplayMetric.densityDpi = targetDensityDpi;

    }

    public static void setCustomWithDensity(@NonNull final Application application, @NonNull Activity activity) {

        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();
        if (sNonCompatDesity == 0) {
            sNonCompatDesity = appDisplayMetric.density;
            sNonCompatScaleDesity = appDisplayMetric.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNonCompatScaleDesity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = 1f * appDisplayMetric.widthPixels / 360;
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDesity / sNonCompatDesity);
        final int targetDensityDpi = (int) (160 * targetDensity);

        activityDisplayMetric.density = targetDensity;
        activityDisplayMetric.scaledDensity = targetScaleDensity;
        activityDisplayMetric.densityDpi = targetDensityDpi;

    }


    public static void setOrigDensity(@NonNull final Application application, @NonNull Activity activity) {
        final DisplayMetrics appDisplayMetric = application.getResources().getDisplayMetrics();
        DisplayMetrics activityDisplayMetric = activity.getResources().getDisplayMetrics();


        activityDisplayMetric.density = appDisplayMetric.density;
        activityDisplayMetric.scaledDensity = appDisplayMetric.scaledDensity;
        activityDisplayMetric.densityDpi = appDisplayMetric.densityDpi;
    }


    /**
     * 获取屏幕的高度绝对高度，备用
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
     * 判断虚拟导航栏是否显示，备用
     *
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
     *
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


    //备用方法，获取屏幕原始尺寸高度，包括虚拟功能键高度
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

    //备用方法，获取屏幕高度 不包含虚拟按键=
    public static int getScreenHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }


    /**
     * 备用方法
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
     * 获取状态栏高度，备用
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


}
