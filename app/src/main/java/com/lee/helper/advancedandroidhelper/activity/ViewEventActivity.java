package com.lee.helper.advancedandroidhelper.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.helper.advancedandroidhelper.R;

public class ViewEventActivity extends AppCompatActivity
{
    public static final String iconUrl = "https://images.tuyaeu.com/smart/icon/15538087493hxt7ei3r21_0.png";
    private Button btnEvent;
    private Button btnResume;
    private Button btnReflect;
    private ImageView ivResume;
    private ImageView ivCheck;
    private TextView tvReflect;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        btnEvent = findViewById(R.id.btn_view_event);
        btnResume = findViewById(R.id.btn_resume);
        ivCheck = findViewById(R.id.iv_check_gray);
        ivResume = findViewById(R.id.iv_check_gray2);
        btnReflect = findViewById(R.id.btn_reflect);
        tvReflect = findViewById(R.id.tv_reflect);
        btnReflect.setOnClickListener(v -> {
            tvReflect.setTypeface(null, Typeface.BOLD);
        });

        btnEvent.setOnClickListener(v -> {
            Bitmap bitmapOrigin = getOriginBitMap();
            if(bitmapOrigin != null){
               Bitmap grayBmp =  toGrayScale(bitmapOrigin);
               ivCheck.setImageBitmap(grayBmp);
            }

            Log.e("EVENTVIEW","onClick <===> ");
        });
        btnEvent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("EVENTVIEW","onTouch <===> ");


                return false;
            }
        });

        btnResume.setOnClickListener(v -> {
            toGrayStyle(ivResume);
        });


    }

    private Bitmap getOriginBitMap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.adv_add_plus);
        return bitmap;
    }

    private Bitmap toGrayScale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        /**
         * 第一步是创建一个"空白的"Bitmap
         * Bitmap.Config.RGB_565 : R占用5bit, G占用6bit, B占用5bit.
         * 与之类似的ARGB_4444，多了一个Alpha值，也占用4bit
         * */
        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        /**
         * Canvas会在这个"空白的"Bitmap上绘制
         * */
        Canvas c = new Canvas(bmpGrayScale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        /**ColorMatrix饱和度为0*/
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        /**利用paint设置颜色规则*/
        paint.setColorFilter(f);
        /**将原有的Bitmap绘制到空白的bitmap上*/
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayScale;
    }


    private void toGrayStyle(ImageView imageView ){
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0); // 设置饱和度
        ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
        imageView.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
    }

}
