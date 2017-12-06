package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by jia on 2017/10/1.
 */

public class EraseFrameLayout extends FrameLayout implements EraseParent {
    private Bitmap canvasBitmap;

    public EraseFrameLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public EraseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EraseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setDrawingCacheEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EraseFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasBitmap = getDrawingCache();
    }

    @Override
    public int[] getPixels(Rect rect) {
        if (canvasBitmap == null) {
            return null;
        }

        int width = rect.width();
        int height = rect.height();

        int[] result = new int[width * height];
        canvasBitmap.getPixels(result, 0, width, rect.left, rect.top, width, height);
        return result;
    }
}
