package com.jiakaiyang.eraseview.lib.drawable;

import com.jiakaiyang.eraseview.lib.shape.EraseShape;

/**
 * Created by bing on 2017/12/10.
 * this is the interface for a Drawable which can be erased when it's been drawn.
 */

public interface EraseDrawable {

    /**
     * erase ap shape of this Drawable when the draw() called.
     *
     * @param shape
     * @param locationX
     * @param locationY
     */
    public void erase(EraseShape shape, float locationX, float locationY);
}
