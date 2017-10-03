package com.jiakaiyang.eraseview.example;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.jiakaiyang.eraseview.lib.EraseImageView;
import com.jiakaiyang.eraseview.lib.EraseView;

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

        ViewTreeObserver observer = eraseView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private boolean hasCalled = false;

            @Override
            public void onGlobalLayout() {
                if (hasCalled) {
                    return;
                }

                Rect rect = new Rect();
                eraseView.getGlobalVisibleRect(rect);

                rect.right = rect.right / 2;
                rect.bottom = rect.bottom / 2;

                int width = rect.width();
                int height = rect.height();

                int[] pixels = new int[width * height];
                for (int i = 0; i < pixels.length; i++) {
                    pixels[i] = 0;
                }

                eraseView.setPixels(rect, pixels);

                hasCalled = true;
            }
        });
    }
}
