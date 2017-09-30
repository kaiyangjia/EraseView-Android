package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by jia on 2017/9/30.
 * <p>
 * The EraseImageView
 */

public class EraseImageView extends AppCompatImageView {
    private Paint erasePaint;

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
        canvas.drawRect(rect, erasePaint);
//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    }

    private Paint createErasePaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        return paint;
    }
}
