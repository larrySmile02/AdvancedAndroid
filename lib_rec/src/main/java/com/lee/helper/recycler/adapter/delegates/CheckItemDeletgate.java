package com.lee.helper.recycler.adapter.delegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.lee.helper.recycler.R;
import com.lee.helper.recycler.beans.CheckItem;
import com.lee.helper.recycler.model.IDevItemModel;

import java.util.List;

public class CheckItemDeletgate extends AdapterDelegate<List<IDevItemModel>> {

    private Context mContext;
    private LayoutInflater inflater;

    public CheckItemDeletgate(Context mContext){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    protected boolean isForViewType(@NonNull List<IDevItemModel> items, int position) {
       return   items.get(position) instanceof CheckItem ;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_click,parent,false);
        return new ClickItemDelegate.ClickViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<IDevItemModel> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {
        CheckItem item = (CheckItem) items.get(position);
        ((ClickItemDelegate.ClickViewHolder)holder).imgCheck.setVisibility(View.VISIBLE);
        ((ClickItemDelegate.ClickViewHolder)holder).imgCheck.setSelected(item.isChecked());
        ((ClickItemDelegate.ClickViewHolder)holder).tvSubTitl.setVisibility(View.GONE);
        ((ClickItemDelegate.ClickViewHolder)holder).tvTitle.setVisibility(View.VISIBLE);
        ((ClickItemDelegate.ClickViewHolder)holder).tvTitle.setText(item.getTitle());
    }
}
