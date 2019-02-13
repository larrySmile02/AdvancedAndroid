package com.lee.helper.advancedandroidhelper.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.uiconfig.Constant;
import com.lee.helper.advancedandroidhelper.uiconfig.GuideActivity;
import com.lee.helper.advancedandroidhelper.uiconfig.MyPersonalActivity;
import com.lee.helper.recycler.RecDevInfoActivity;
import com.lee.helper.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class RecMainAdapter extends RecyclerView.Adapter
{
    private List<String> dataItems =  new ArrayList<>();
    private LayoutInflater inflater;
    private Activity mActivity;

    public RecMainAdapter(Activity mActivity,List<String> dataItems){
        this.dataItems = dataItems;
        this.mActivity = mActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int pos) {
        inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter_main,viewGroup,false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int pos) {
        String title = dataItems.get(pos);
        ((RecViewHolder)holder).tvName.setText(title);
        ((RecViewHolder)holder).lltContainer.setOnClickListener(v -> {
            if(title.contains(Constant.CONFIG)){
                mActivity.startActivity((new Intent(mActivity, GuideActivity.class)));
            }else if(title.contains(Constant.TOAST)){
                ToastUtils.preToast(mActivity);
                ToastUtils.show(mActivity.getResources().getString(R.string.click_me));
            }else if(title.contains(Constant.RECYCLERVIEW)){
                mActivity.startActivity(new Intent(mActivity, RecDevInfoActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    class RecViewHolder extends RecyclerView.ViewHolder{

        LinearLayout lltContainer;
        TextView tvName;
        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.item_name);
            lltContainer = itemView.findViewById(R.id.name_container);
        }
    }
}
