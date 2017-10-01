package com.jiakaiyang.eraseview.lib;

import android.graphics.Rect;

/**
 * Created by jia on 2017/10/1.
 * the child interface for EraseView
 */

public interface EraseChild {

    /**
     * set pixels with given data
     *
     * @param rect
     * @param pixels
     */
    public void setPixels(Rect rect, int[] pixels);

}
