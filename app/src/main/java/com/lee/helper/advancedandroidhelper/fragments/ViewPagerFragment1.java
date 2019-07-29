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
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;

public class ViewPagerFragment1 extends Fragment {

    private final String TAG = ViewPagerFragment1.class.getSimpleName();
    private TextView tvNum;
    private int num = -1;

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        if(args != null){
            num = args.getInt("POSTION");
        }
        Log.e(TAG,"setArguments============> num="+num);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TAG,"onAttach============"+num);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager1,container,false);
        tvNum = view.findViewById(R.id.tvNum);
        tvNum.setText(num+"");
        Log.e(TAG,"onCreateView============"+num);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,"onResume============"+num);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,"onResume============"+num);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,"onResume============"+num);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy============"+num);
    }
}
