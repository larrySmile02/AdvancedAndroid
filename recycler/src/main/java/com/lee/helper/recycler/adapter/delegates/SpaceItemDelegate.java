package com.lee.helper.recycler.adapter.delegates;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.adapterdelegates3.AdapterDelegate;
import com.lee.helper.recycler.R;
import com.lee.helper.recycler.beans.SpaceItem;
import com.lee.helper.recycler.model.IDevItemModel;

import java.util.List;

public class SpaceItemDelegate extends AdapterDelegate<List<IDevItemModel>> {

    private Context mContext;
    private LayoutInflater inflater;

    public SpaceItemDelegate(Context mContext){
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    protected boolean isForViewType(@NonNull List<IDevItemModel> items, int position) {
        return items.get(position) instanceof SpaceItem;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.adapter_item_space,parent,false);
        return new SpaceViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull List<IDevItemModel> items, int position, @NonNull RecyclerView.ViewHolder holder, @NonNull List<Object> payloads) {

    }

    class SpaceViewHolder extends RecyclerView.ViewHolder{

        public SpaceViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
