package com.lee.helper.config;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;


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

        final float targetDensity = 1f * appDisplayMetric.heightPixels / 640;
        final float targetScaleDensity = targetDensity * (sNonCompatScaleDesity / sNonCompatDesity);
        final int targetDensityDpi = (int) (160 * targetDensity);

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

}
