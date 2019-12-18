package com.lee.helper.advancedandroidhelper.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyViewPager extends ViewPager {

    private boolean isVertical = false; //垂直
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setVertical(boolean vertical) {
        isVertical = vertical;
        if (isVertical) {
            setOverScrollMode(OVER_SCROLL_NEVER);
            setPageTransformer(true, new VerticalPageTransformer());
        } else {
            setPageTransformer(true, null);
        }
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        int count = getChildCount();
        for(int i = 0; i < count; i++ ){
            View child = this.getChildAt(i);
            ViewPager.LayoutParams lp = (ViewPager.LayoutParams)child.getLayoutParams();
            if (!lp.isDecor) {
                Log.e("my_viewpager","i= "+i+"; child.getLeft() = "+child.getLeft() +" scrollX = "+this.getScrollX() +" hashCode = "+child);
            }
        }

    }

    private class VerticalPageTransformer implements PageTransformer {

        @Override
        public void transformPage(View view, float position) {

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                view.setAlpha(1);

                // Counteract the default slide transition
                float xPosition = view.getWidth() * -position;
                view.setTranslationX(xPosition);

                //set Y position to swipe in from top
                float yPosition = position * view.getHeight();
                view.setTranslationY(yPosition);

                Log.e("MY_VIEW", "  postion = "+position+" ; xPosition = "+xPosition+" ; yPosition="+yPosition+" ; view =" +view);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
