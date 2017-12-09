package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.jiakaiyang.eraseview.lib.drawable.EraseBitmapDrawable;
import com.jiakaiyang.eraseview.lib.utils.ClassUtils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static com.jiakaiyang.eraseview.lib.utils.ClassUtils.getTargetField;

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
    private int eraseToLayer = Layer.CURRENT_BACKGROUND;

    protected Canvas mCanvas;

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
//        setDrawingCacheEnabled(true);
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
        if (mCanvas == null) {
            mCanvas = canvas;
        }

        try {
            Field field = ClassUtils.getDeclaredField(this, "mDrawable");
            field.setAccessible(true);

            Drawable drawable = (Drawable) field.get(this);
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                EraseBitmapDrawable proxyDrawable = new EraseBitmapDrawable(bitmapDrawable);
                field.set(this, proxyDrawable);
            } else {
                // TODO: 2017/12/8 handle other type Drawable
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        super.onDraw(canvas);
    }

    @Override
    public void setPixels(final Rect rect, final int[] pixels) {
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
    }

    private Paint createErasePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
//        paint.setColor(Color.TRANSPARENT);
        paint.setAlpha(0xFF);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

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
