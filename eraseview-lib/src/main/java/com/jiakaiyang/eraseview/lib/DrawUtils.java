package com.jiakaiyang.eraseview.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by jia on 2017/9/30.
 */

public class DrawUtils {
    private static final String TAG = "DrawUtils";

    /**
     * get the bitmap of canvas
     *
     * @param canvas
     * @return
     */
    public static Bitmap getCanvasBitmap(Canvas canvas) {
        if (canvas == null) {
            return null;
        }

        Bitmap result = null;

        Class clazz = canvas.getClass();
        try {
//            Field bitmapField = clazz.getDeclaredField("mBitmap");
            Field bitmapField = ObjectUtils.getDeclaredMethod(canvas, "mBitmap");

            boolean saveAccessible = bitmapField.isAccessible();
            if (!saveAccessible) {
                bitmapField.setAccessible(true);
            }

            result = (Bitmap) bitmapField.get(canvas);

            bitmapField.setAccessible(saveAccessible);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "getCanvasBitmap: IllegalAccessException: " + e.getMessage());
        }

        return result;
    }
}
