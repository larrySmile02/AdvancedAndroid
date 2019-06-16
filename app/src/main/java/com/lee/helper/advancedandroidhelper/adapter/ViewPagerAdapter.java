package com.lee.helper.advancedandroidhelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter
{
    private List<String> datas = new ArrayList<>();
    private Context mContext;
    private LayoutInflater inflater;

    public ViewPagerAdapter(Context mContext, List<String>datas){
        this.mContext = mContext;
        this.datas = datas;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //不能把null换成container,false,否则加载不出来
        View view = inflater.inflate(R.layout.adapter_viewpager,null);
        String title = datas.get(position);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        //必须这里add
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
