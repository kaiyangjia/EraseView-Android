package com.jiakaiyang.eraseview.lib.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;

/**
 * Created by jia on 2017/10/4.
 */

public class EraseColorDrawable extends ColorDrawable {
    private static final String TAG = "EraseColorDrawable";

    private Rect eraseRect;
    private Paint paint;

    private
    @ColorInt
    int eraseColor;

    public EraseColorDrawable() {
    }

    public EraseColorDrawable(@ColorInt int color) {
        super(color);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        resetPaint();
        canvas.drawRect(eraseRect, paint);
    }

    private void resetPaint() {
        if (paint == null) {
            paint = new Paint();
        }

        paint.setColor(eraseColor);
    }

    public Rect getEraseRect() {
        return eraseRect;
    }

    public void setEraseRect(Rect eraseRect) {
        this.eraseRect = eraseRect;
    }

    public int getEraseColor() {
        return eraseColor;
    }

    public void setEraseColor(int eraseColor) {
        this.eraseColor = eraseColor;
    }
}
