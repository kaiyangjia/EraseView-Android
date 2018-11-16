package com.jiakaiyang.eraseview.lib.shape;

/**
 * Created by bing on 2017/12/10.
 * A Shape
 */

public abstract class EraseShape {

    public static final int TYPE_RECT = 0;
    public static final int TYPE_OVAL = 1;
    public static final int TYPE_CIRCLE =2;

    private float mWidth;
    private float mHeight;

    // the type of this shape. The child class should set this field.
    protected int type;

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }
}
