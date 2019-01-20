package com.lee.helper.advancedandroidhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lee.helper.advancedandroidhelper.uiconfig.GuideActivity;
import com.lee.helper.advancedandroidhelper.uiconfig.MyPersonalActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, GuideActivity.class));
    }
}
