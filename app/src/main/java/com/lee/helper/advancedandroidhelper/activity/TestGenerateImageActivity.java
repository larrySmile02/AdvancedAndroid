package com.lee.helper.advancedandroidhelper.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lee.helper.advancedandroidhelper.R;
import com.lee.helper.advancedandroidhelper.utils.GenerateImageLogic;

public class TestGenerateImageActivity extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_img);
        iv = findViewById(R.id.iv_img);
        Bitmap bitmap = GenerateImageLogic.getIns().getBitmap();
        if(bitmap != null ){
            iv.setImageBitmap(bitmap);
        }

    }
}
