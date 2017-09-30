package com.jiakaiyang.eraseview.example;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jiakaiyang.eraseview.lib.EraseImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        EraseImageView imageView = (EraseImageView) findViewById(R.id.image);
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Log.i(TAG, "initView: drawable is BitmapDrawable");
        }
    }
}
