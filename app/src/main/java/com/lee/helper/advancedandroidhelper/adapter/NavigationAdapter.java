package com.lee.helper.advancedandroidhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;

import java.util.List;

public class NavigationAdapter extends BaseAdapter
{
    private List<String> list ;
    private LayoutInflater inflater;

    public NavigationAdapter(Context mContext, List<String> list ){
        this.list = list;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.adapter_navigation_view,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(list.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView title;
        ViewHolder(View view){
            title = view.findViewById(R.id.tvMenu_item);
        }
    }
}
