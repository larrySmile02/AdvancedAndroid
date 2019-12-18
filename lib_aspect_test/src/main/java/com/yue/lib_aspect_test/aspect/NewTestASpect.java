package com.yue.lib_aspect_test.aspect;

import android.util.Log;

import com.yue.lib.sample.TestAspectActivity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class NewTestASpect {

    @Before("execution(* com.yue.lib_aspect_test.aspect.SubTestAspectActivity.comeSubActivity(..))")
    public void beferComeSubActivity(JoinPoint joinPoint){
        Log.e(TestAspectActivity.TAG,"beferComeSubActivity ==>");
    }

    @Before("execution(* com.yue.lib_aspect_test.aspect.SubTestAspectActivity.welecome**(..))")
    public void beforWelcome(JoinPoint joinPoint){
        Log.e(TestAspectActivity.TAG,"welcome <========>");
    }
}
