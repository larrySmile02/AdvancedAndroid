package com.lee.helper.advancedandroidhelper.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.bean.NewsItem;

import java.util.List;

public class RecNewsAdapter extends RecyclerView.Adapter {

    private List<NewsItem> items;
    private Context mContext;

    public RecNewsAdapter(Context mContext ,List<NewsItem> items){
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_news,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        NewsItem currentItem = items.get(pos);
        ((NewsViewHolder)viewHolder).tvTitle.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title_news);
        }
    }
}
