package com.jiakaiyang.eraseview.lib;

import android.graphics.Rect;

/**
 * Created by jia on 2017/10/1.
 * The parent interface for EraseView
 */

public interface EraseParent {

    /**
     * get pixels of rect
     *
     * @param rect
     * @return
     */
    public int[] getPixels(Rect rect);
}
