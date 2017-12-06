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

/**
 * Created by jia on 2017/9/30.
 * <p>
 * The EraseImageView
 */

public class EraseImageView extends AppCompatImageView {
    private static final String TAG = "EraseImageView";

    private Paint erasePaint;

    // The layer eraseToLayer will not be erase
    // Between top and eraseToLayer will be erase
    private int eraseToLayer = Layer.PARENT_BACKGROUND;

    public EraseImageView(Context context) {
        super(context);
    }

    public EraseImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EraseImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (erasePaint == null) {
            erasePaint = createErasePaint();
        }

        int marginLeft = getLeft();
        int marginTop = getTop();


        Rect rect = new Rect(0, 0, getWidth() / 2, getHeight() / 2);
//        boolean clipResult = canvas.clipRect(rect);
//        Log.i(TAG, "onDraw: clipResult: " + clipResult);
        //        canvas.drawRect(rect, recerasePaint);

        Rect roundRect = new Rect();
        getRoundRect(roundRect);

        Drawable background = getTargetDrawable();
        background.setBounds(roundRect);
        background.draw(canvas);
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
