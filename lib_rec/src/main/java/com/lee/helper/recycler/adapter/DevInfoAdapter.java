package com.lee.helper.recycler.adapter;

import android.content.Context;

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter;
import com.lee.helper.recycler.adapter.delegates.CheckItemDeletgate;
import com.lee.helper.recycler.adapter.delegates.ClickItemDelegate;
import com.lee.helper.recycler.adapter.delegates.SpaceItemDelegate;
import com.lee.helper.recycler.model.IDevItemModel;

import java.util.List;

public class DevInfoAdapter extends ListDelegationAdapter<List<IDevItemModel>>
{
    public DevInfoAdapter(Context mContext){
        delegatesManager.addDelegate(new CheckItemDeletgate(mContext));
        delegatesManager.addDelegate(new ClickItemDelegate(mContext));
        delegatesManager.addDelegate(new SpaceItemDelegate(mContext));
    }
}
