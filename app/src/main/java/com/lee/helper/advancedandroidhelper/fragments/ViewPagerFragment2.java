package com.lee.helper.advancedandroidhelper.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lee.helper.advancedandroidhelper.R;

public class ViewPagerFragment2 extends Fragment {

    private final String TAG = ViewPagerFragment2.class.getSimpleName();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach============");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager2,container,false);
        Log.e(TAG,"onCreateView============");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume============");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy============");
    }
}
