package com.jiakaiyang.eraseview.example;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.jiakaiyang.eraseview.lib.EraseFrameLayout;
import com.jiakaiyang.eraseview.lib.EraseImageView;
import com.jiakaiyang.eraseview.lib.EraseView;
import com.jiakaiyang.eraseview.lib.ObjectUtils;
import com.jiakaiyang.eraseview.lib.OnDrawListener;
import com.jiakaiyang.eraseview.lib.drawable.EraseColorDrawable;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        final EraseImageView eraseView = (EraseImageView) findViewById(R.id.eraseView);
        final EraseFrameLayout parentView = (EraseFrameLayout) eraseView.getParent();

        eraseView.addOnDrawListener(new OnDrawListener() {
            @Override
            public void onDraw(Canvas canvas) {
                Rect rect = new Rect();
                eraseView.getGlobalVisibleRect(rect);

                rect.right = (rect.right + rect.left) / 2;
                rect.bottom = (rect.bottom + rect.top) / 2;

//                int[] pixels = parentView.getPixels(rect);
//                eraseView.setPixels(rect, pixels);


                EraseColorDrawable eraseColorDrawable = new EraseColorDrawable(Color.GRAY);
                eraseColorDrawable.setEraseColor(Color.CYAN);
                eraseColorDrawable.setEraseRect(rect);

                Field field = ObjectUtils.getDeclaredField(eraseView, "mBackground");
                try {
                    field.setAccessible(true);
                    field.set(eraseView, eraseColorDrawable);
                } catch (IllegalAccessException e) {
                    Log.e(TAG, "onDraw: IllegalAccessException: " + e.getMessage());
                }
            }
        });


    }
}
