package com.jiakaiyang.eraseview.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by jia on 2017/12/4.
 */

public class ViewUtils {

    public static Drawable forkBackground(View view) {
        Drawable drawable = view.getBackground();
        return drawable.getConstantState().newDrawable();
    }


    public static Drawable forkSrc(ImageView imageView) {
        return imageView.getDrawable();
    }


    /**
     * get The View Bitmap, The View must be displayed
     * @param v
     * @return
     */
    public static Bitmap getBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
}
