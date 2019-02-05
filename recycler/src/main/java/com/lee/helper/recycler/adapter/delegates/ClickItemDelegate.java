package com.lee.helper.recycler.adapter.delegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.lee.helper.recycler.R;
import com.lee.helper.recycler.beans.ClickItem;
import com.lee.helper.recycler.model.IDevItemModel;
import com.lee.helper.toast.ToastUtils;

import java.util.List;

public class ClickItemDelegate extends AdapterDelegate<List<IDevItemModel>>
{
    private Context mContext;
    private LayoutInflater inflater;

    public ClickItemDelegate(Context mContext){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    protected boolean isForViewType(@NonNull List<IDevItemModel> items, int position) {
        return items.get(position) instanceof ClickItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_click,parent,false);
        return new ClickViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<IDevItemModel> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        ClickItem item = (ClickItem) items.get(position);
        ((ClickViewHolder)holder).imgCheck.setVisibility(View.GONE);
        ((ClickViewHolder)holder).tvTitle.setText(item.getTitle());
        ((ClickViewHolder)holder).tvSubTitl.setText(item.getSubTitle());
        ((ClickViewHolder)holder).tvSubTitl.setOnClickListener(v -> {
            ToastUtils.show(mContext.getString(R.string.recycler_click_me));});
    }

    public static class  ClickViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvSubTitl;
        ImageView imgCheck;

        public ClickViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.menu_list_title);
            tvSubTitl = itemView.findViewById(R.id.menu_list_sub_title);
            imgCheck = itemView.findViewById(R.id.iv_checked);
        }
    }
}
