package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jia on 2017/9/30.
 * <p>
 * The EraseImageView
 */


public class EraseImageView extends AppCompatImageView implements EraseChild {
    private static final String TAG = "EraseImageView";

    private Set<OnDrawListener> mOnDrawListeners;

    private Paint erasePaint;

    // The layer eraseToLayer will not be erase
    // Between top and eraseToLayer will be erase
    private int eraseToLayer = Layer.PARENT_BACKGROUND;

    protected Canvas mCanvas;
    protected Bitmap mCanvasBitmap;

    public EraseImageView(Context context) {
        this(context, null, 0);
    }

    public EraseImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EraseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setDrawingCacheEnabled(true);
    }

    public boolean addOnDrawListener(OnDrawListener listener) {
        if (listener == null) {
            return false;
        }

        if (mOnDrawListeners == null) {
            mOnDrawListeners = new HashSet<>();
        }

        boolean result = mOnDrawListeners.add(listener);
        if (!result) {
            Log.w(TAG, "addLisaddOnDrawListenertener: add listener failure");
        }

        return result;
    }

    public boolean removeOnDrawListener(OnDrawListener listener) {
        if (mOnDrawListeners == null
                || listener == null) {
            Log.w(TAG, "removeOnDrawListener: listener or mOnDrawListeners is null return");
            return false;
        }

        boolean result = mOnDrawListeners.remove(listener);
        if (!result) {
            Log.w(TAG, "removeOnDrawListener: remove listener failure");
        }

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mCanvasBitmap = getDrawingCache();

        if (mCanvas == null) {
            mCanvas = canvas;
        }

        if (erasePaint == null) {
            erasePaint = createErasePaint();
        }

        int marginLeft = getLeft();
        int marginTop = getTop();

//        Rect rect = new Rect(0, 0, getWidth() / 2, getHeight() / 2);
//        canvas.drawRect(rect, erasePaint);

        if (mOnDrawListeners != null) {
            for (OnDrawListener listener : mOnDrawListeners) {
                listener.onDraw(canvas);
            }
        }
    }

    @Override
    public void setPixels(final Rect rect, final int[] pixels) {
        if (mCanvasBitmap == null) {
            Log.e(TAG, "setPixels: mCanvasBitmap is null, onDraw has not been called");
            return;
        }

        onSetPixels(rect, pixels);
    }

    private void onSetPixels(Rect rect, int[] pixels) {
        int width = rect.width();
        int height = rect.height();


//        boolean clipResult = canvas.clipRect(rect);
//        Log.i(TAG, "onDraw: clipResult: " + clipResult);
        //        canvas.drawRect(rect, recerasePaint);

        Rect roundRect = new Rect();
        getRoundRect(roundRect);

        Drawable background = getTargetDrawable();
        background.setBounds(roundRect);

        mCanvasBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
    }

    private Paint createErasePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
//        paint.setColor(Color.TRANSPARENT);
        paint.setAlpha(0xFF);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        return paint;
    }

    /**
     * 获取需要被擦出区域的Drawable
     *
     * @return
     */
    protected Drawable getTargetDrawable() {
        switch (eraseToLayer) {
            case Layer.PARENT_BACKGROUND:
                return ViewUtils.forkBackground((View) getParent());
            case Layer.CURRENT_BACKGROUND:
                return ViewUtils.forkBackground(this);
        }

        return null;
    }


    protected Bitmap getTargetBitmap() {
        switch (eraseToLayer) {
            case Layer.PARENT_BACKGROUND:
                return ViewUtils.getBitmapFromView((View) getParent());
            case Layer.CURRENT_BACKGROUND:
                return ViewUtils.getBitmapFromView(this);
        }

        return null;
    }


    protected void getRoundRect(Rect rect) {
        switch (eraseToLayer) {
            case Layer.PARENT_BACKGROUND: {
                View view = (View) getParent();
                view.getGlobalVisibleRect(rect);
                break;
            }
            case Layer.CURRENT_BACKGROUND: {
                getGlobalVisibleRect(rect);
                break;
            }
        }
    }
}
