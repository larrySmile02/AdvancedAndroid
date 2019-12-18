package com.lee.helper.advancedandroidhelper.widgit;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**为了研究ViewPager的事件拦截机制*/
public class MyConstraintLayout extends ConstraintLayout {
    public MyConstraintLayout(Context context) {
        this(context,null);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
