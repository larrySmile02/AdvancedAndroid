package com.lee.helper.advancedandroidhelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.lee.helper.advancedandroidhelper.R;

public class TestAnimatiorActivity extends AppCompatActivity implements View.OnClickListener {

    private final int ANIM_ALPHA = 0;
    private final int ANIM_SCALE = 1;
    private final int ANIM_TRANS = 2;

    private ImageView ivGirl;
    private Animation animation;
    private Button btn;
    private int index;
    private Animation.AnimationListener animationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ivGirl = findViewById(R.id.iv_sexy_girl);
        btn = findViewById(R.id.btn_anim);
        btn.setOnClickListener(this);
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                index++;
                if (index > ANIM_TRANS) index = 0;
                switch (index) {
                    case ANIM_ALPHA:
                        btn.setText(R.string.anim_change_scale);
                        break;
                    case ANIM_SCALE:
                        btn.setText(R.string.anim_translate);
                        break;
                    case ANIM_TRANS:
                        btn.setText(R.string.anim_change_alpha);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_anim) {
            switch (index) {
                case ANIM_ALPHA:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_alpha);
                    break;
                case ANIM_SCALE:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_scale);
                    break;
                case ANIM_TRANS:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_trans);
                    break;
                default:
                    break;
            }
            animation.setAnimationListener(animationListener);
            animation.setFillAfter(false); //默认也是false,为了演示方便。
            ivGirl.startAnimation(animation);

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        animationListener = null;
    }
}
