package com.lee.helper.advancedandroidhelper.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.lee.helper.advancedandroidhelper.R;

public class TestAnimatiorActivity extends AppCompatActivity implements View.OnClickListener {

    private final int ANIM_ALPHA = 0;
    private final int ANIM_SCALE = 1;
    private final int ANIM_TRANS = 2;
    private final int ANIM_MAX = 3;

    private ImageView ivGirl;
    private Button btn;
    private Button viewPropertyBtn;
    private Button objectPropertyBtn;

    private Animation animation;
    IndexHolder indexHolder;
    private Animation.AnimationListener animationListener;

    private ViewPropertyAnimator viewPropertyAnimator;
    private Animator.AnimatorListener viewPropertyAnimatorListener;

    private ObjectAnimator objectAnimator;
    private Animator.AnimatorListener propertyAnimatorListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ivGirl = findViewById(R.id.iv_sexy_girl);
        btn = findViewById(R.id.btn_anim);
        btn.setOnClickListener(this);
        viewPropertyBtn = findViewById(R.id.btn_anim_view);
        viewPropertyBtn.setOnClickListener(this);
        objectPropertyBtn = findViewById(R.id.btn_anim_property);
        objectPropertyBtn.setOnClickListener(this);
        indexHolder = new IndexHolder();

        viewPropertyAnimator = ivGirl.animate();
        animationListener = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                indexHolder.increaseIndex();
                performBtn(indexHolder.index,btn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };

        viewPropertyAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                indexHolder.increaseViewIndex();
                performBtn(indexHolder.viewIndex,viewPropertyBtn);
                resumeIvGirl();
//                ivGirl.setAlpha(1f);
//                ivGirl.setScaleX(1f);
//                ivGirl.setScaleY(1f);
//                ivGirl.setTranslationX(0);
//                ivGirl.setTranslationY(0);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };

        viewPropertyAnimator.setListener(viewPropertyAnimatorListener);

        propertyAnimatorListener = new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                indexHolder.increasePropertyIndex();
                performBtn(indexHolder.propertyIndex,objectPropertyBtn);
                resumeIvGirl();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };


    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btn_anim) {
            switch (indexHolder.index) {
                case ANIM_ALPHA:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_alpha);
                    break;
                case ANIM_SCALE:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_scale);
                    break;
                case ANIM_TRANS:
                    animation = AnimationUtils.loadAnimation(this, R.anim.anim_base_trans);
                    break;
                case ANIM_MAX:
                    animation = AnimationUtils.loadAnimation(this,R.anim.anim_base_mix);
                default:
                    break;
            }
            animation.setAnimationListener(animationListener);
            animation.setFillAfter(false); //默认也是false,为了演示方便。
            ivGirl.startAnimation(animation);

        }else if(id == R.id.btn_anim_view){
            switch (indexHolder.viewIndex){
                case ANIM_ALPHA:
                    viewPropertyAnimator
                            .setDuration(2000)
                            .alpha(0)
                            .setInterpolator(new AccelerateDecelerateInterpolator());
                    break;
                case ANIM_SCALE:
                    viewPropertyAnimator
                            .setDuration(2000)
                            .scaleX(0)
                            .scaleY(0);
                    break;
                case ANIM_TRANS :
                    viewPropertyAnimator
                            .setDuration(2000)
                            .translationX(100)
                            .translationY(100)
                            .setInterpolator(new OvershootInterpolator());
                    break;
                case ANIM_MAX:
                    viewPropertyAnimator
                            .setDuration(2000)
                            .alpha(0)
                            .scaleX(0)
                            .scaleY(0)
                            .translationX(100)
                            .translationY(100)
                            .setInterpolator(new OvershootInterpolator());
                    break;
            }
        }else if(id == R.id.btn_anim_property){
            switch (indexHolder.propertyIndex){
                case ANIM_ALPHA:
                    objectAnimator = ObjectAnimator.ofFloat(ivGirl,"alpha",1,0);
                    break;
                case ANIM_SCALE:
                    objectAnimator = ObjectAnimator.ofFloat(ivGirl,"scaleX",1,0);

                    break;
                case ANIM_TRANS:
                    objectAnimator = ObjectAnimator.ofFloat(ivGirl,"translationX",0,100);
                    break;
                case ANIM_MAX:
                    PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat("rotation",0,360);
                    PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX",1,0);
                    PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY",1,0);
                    objectAnimator = ObjectAnimator.ofPropertyValuesHolder(ivGirl,rotation,scaleX,scaleY);
                    break;

            }
            objectAnimator.setDuration(2000);
            objectAnimator.addListener(propertyAnimatorListener);
            objectAnimator.start();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        animationListener = null;
        viewPropertyAnimatorListener = null;
        propertyAnimatorListener = null;
    }

    private void performBtn(int pos, View view){

        if(view instanceof Button) {
            switch (pos) {
                case ANIM_ALPHA:
                    ((Button)view).setText(R.string.anim_change_alpha);
                    break;
                case ANIM_SCALE:
                    ((Button)view).setText(R.string.anim_change_scale);
                    break;
                case ANIM_TRANS:
                    ((Button)view).setText(R.string.anim_translate);
                    break;
                case ANIM_MAX:
                    ((Button)view).setText(R.string.anim_mix);
                    break;
                default:
                    break;
            }
        }

    }

    private void resumeIvGirl(){
        ivGirl.setAlpha(1f);
        ivGirl.setScaleX(1f);
        ivGirl.setScaleY(1f);
        ivGirl.setTranslationX(0);
        ivGirl.setTranslationY(0);
    }

    class IndexHolder{
        int index; // View动画index
        int viewIndex; //ViewProperty动画
        int propertyIndex; //property动画计数器

        public void increaseIndex(){
            index++;
            if(index > ANIM_MAX) index = 0;
        }

        public void increaseViewIndex(){
            viewIndex++;
            if(viewIndex > ANIM_MAX) viewIndex = 0;
        }

        public void increasePropertyIndex(){
            propertyIndex++;
            if(propertyIndex > ANIM_MAX) propertyIndex = 0;
        }
    }


}
