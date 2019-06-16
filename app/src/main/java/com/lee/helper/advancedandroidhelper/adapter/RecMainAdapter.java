package com.lee.helper.advancedandroidhelper.adapter;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.model.IMainActivity;
import com.lee.helper.advancedandroidhelper.uiconfig.Constant;
import com.lee.helper.advancedandroidhelper.uiconfig.GuideActivity;
import com.lee.helper.recycler.RecDevInfoActivity;
import com.lee.helper.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class RecMainAdapter extends RecyclerView.Adapter
{
    private List<String> dataItems =  new ArrayList<>();
    private LayoutInflater inflater;
    private Activity mActivity;
    private IMainActivity listener;
    private Animation animation;

    public RecMainAdapter(Activity mActivity,List<String> dataItems , IMainActivity listener){
        this.dataItems = dataItems;
        this.mActivity = mActivity;
        this.listener = listener;
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
            }else if (title.contains(Constant.SERVICE)){
                listener.onStartMyService();
            }else if(title.contains(Constant.REMOTE)){
                listener.onStartRemoteService();
            }else if(title.contains(Constant.REMOTE_MSG)){
                listener.onSetRemoteMsg();
            }else if(title.contains(Constant.JOB_INTENT)){
                listener.onStartJobIntentService();
            }else if(title.contains(Constant.SLIDE_VIEW)){
                listener.gotoSlideView();
            }else if(title.contains(Constant.FLUTTER_MAIN)){
                listener.gotoFlutterMain();
            }else if(title.contains(Constant.ANIMITOR)){
                listener.gotoAnimator();
            }else if(title.contains(Constant.NEWS)){
                listener.gotoNews();
            }else if(title.contains(Constant.EVENT_TEST)){
                listener.gotoEventTest();
            }else if(title.contains(Constant.NOTIFY_TEST)){
                listener.gotoNotifyCation();
            }
        });
        if(isOdd(pos) ){
            animation = AnimationUtils.loadAnimation(mActivity,R.anim.anim_trans_right);
        }else {
            animation = AnimationUtils.loadAnimation(mActivity,R.anim.anim_trans_left);
        }
        animation.setStartOffset(pos * 100);
        View currentView = ((RecViewHolder) holder).getView();
        if(currentView != null) currentView.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    private boolean isOdd(int pos){
        if(pos % 2 == 1)
            return true;
        else
            return false;
    }


    class RecViewHolder extends RecyclerView.ViewHolder{

        LinearLayout lltContainer;
        TextView tvName;
        View itemView;
        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = itemView.findViewById(R.id.item_name);
            lltContainer = itemView.findViewById(R.id.name_container);
        }

        View getView(){
            return itemView;
        }
    }
}
