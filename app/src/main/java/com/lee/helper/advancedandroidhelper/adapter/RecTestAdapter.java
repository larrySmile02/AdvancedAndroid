package com.lee.helper.advancedandroidhelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;

import java.util.List;

public class RecTestAdapter extends RecyclerView.Adapter {

    private List<String> list;
    private Context mContext;

    public  RecTestAdapter(Context mContext, List<String> list){
        this.mContext = mContext;
        this.list = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_rec_test,viewGroup,false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        String title = list.get(pos);
        ((RecViewHolder)viewHolder).tv.setText(title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title_test);
        }
    }
}
