package com.lee.helper.advancedandroidhelper.anim;

import android.animation.TypeEvaluator;
import android.util.Log;

public class MyElvater implements TypeEvaluator {

    int valueIndex = -1;

    @Override
    public Integer evaluate(float fraction, Object startValue, Object endValue) {
        if(fraction > 0.2 && fraction < 0.4)
            valueIndex = 0;
        else if(fraction > 0.4 && fraction < 0.6)
            valueIndex = 1;
        else if(fraction > 0.6 && fraction < 0.8)
            valueIndex = 2;
        else if(fraction > 0.8 && fraction < 1)
            valueIndex = 3;
        else if(fraction == 1 )
            valueIndex = -1;
        return valueIndex;
    }
}
