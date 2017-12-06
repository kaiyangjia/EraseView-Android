package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by jia on 2017/9/30.
 */

public class EraseView extends View implements EraseChild {
    private static final String TAG = "EraseView";

    protected Canvas mCanvas;
    protected Bitmap mCanvasBitmap;

    public EraseView(Context context) {
        super(context);
    }

    public EraseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EraseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EraseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mCanvas == null) {
            mCanvas = canvas;
            mCanvasBitmap = DrawUtils.getCanvasBitmap(mCanvas);
        }
    }

    @Override
    public void setPixels(Rect rect, int[] pixels) {
        if (mCanvasBitmap == null) {
            Log.e(TAG, "setPixels: mCanvasBitmap is null, onDraw has not been called");
            return;
        }

        int width = rect.width();
        int height = rect.height();
        mCanvasBitmap.setPixels(pixels, 0, width, rect.left, rect.top, width, height);
    }
}
