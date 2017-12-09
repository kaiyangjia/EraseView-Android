package com.jiakaiyang.eraseview.lib.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

/**
 * Created by jia on 2017/12/8.
 * The Bitmap with Hook
 */

public class EraseBitmapDrawable extends BitmapDrawable {
    private static final String TAG = "EraseBitmapDrawable";

    private BitmapDrawable mBase;


    public EraseBitmapDrawable(BitmapDrawable mBase) {
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(Resources res, BitmapDrawable mBase) {
        super(res);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(Bitmap bitmap, BitmapDrawable mBase) {
        super(bitmap);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(Resources res, Bitmap bitmap, BitmapDrawable mBase) {
        super(res, bitmap);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(String filepath, BitmapDrawable mBase) {
        super(filepath);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(Resources res, String filepath, BitmapDrawable mBase) {
        super(res, filepath);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(InputStream is, BitmapDrawable mBase) {
        super(is);
        this.mBase = mBase;
    }

    public EraseBitmapDrawable(Resources res, InputStream is, BitmapDrawable mBase) {
        super(res, is);
        this.mBase = mBase;
    }

    private void beforeDraw(Canvas canvas) {
        Bitmap bitmap = mBase.getBitmap();
        if (bitmap == null) {
            Log.i(TAG, "beforeDraw: bitmap is null");
            return;
        }
        try {
            Field field = bitmap.getClass().getDeclaredField("mIsMutable");
            field.setAccessible(true);
            field.set(bitmap, true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                bitmap.setPixel(i, j, Color.TRANSPARENT);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        beforeDraw(canvas);
        mBase.draw(canvas);
    }

    @Override
    public void setTargetDensity(Canvas canvas) {
        mBase.setTargetDensity(canvas);
    }

    @Override
    public void setTargetDensity(DisplayMetrics metrics) {
        mBase.setTargetDensity(metrics);
    }

    @Override
    public void setTargetDensity(int density) {
        mBase.setTargetDensity(density);
    }

    @Override
    public int getGravity() {
        return mBase.getGravity();
    }

    @Override
    public void setGravity(int gravity) {
        mBase.setGravity(gravity);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void setMipMap(boolean mipMap) {
        mBase.setMipMap(mipMap);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public boolean hasMipMap() {
        return mBase.hasMipMap();
    }

    @Override
    public void setAntiAlias(boolean aa) {
        mBase.setAntiAlias(aa);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public boolean hasAntiAlias() {
        return mBase.hasAntiAlias();
    }

    @Override
    public void setFilterBitmap(boolean filter) {
        mBase.setFilterBitmap(filter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean isFilterBitmap() {
        return mBase.isFilterBitmap();
    }

    @Override
    public void setDither(boolean dither) {
        mBase.setDither(dither);
    }

    @Override
    public Shader.TileMode getTileModeX() {
        return mBase.getTileModeX();
    }

    @Override
    public Shader.TileMode getTileModeY() {
        return mBase.getTileModeY();
    }

    @Override
    public void setTileModeX(Shader.TileMode mode) {
        mBase.setTileModeX(mode);
    }

    @Override
    public void setTileModeXY(Shader.TileMode xmode, Shader.TileMode ymode) {
        mBase.setTileModeXY(xmode, ymode);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void setAutoMirrored(boolean mirrored) {
        mBase.setAutoMirrored(mirrored);
    }

    @Override
    public int getChangingConfigurations() {
        return mBase.getChangingConfigurations();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void getOutline(@NonNull Outline outline) {
        mBase.getOutline(outline);
    }

    @Override
    public void setAlpha(int alpha) {
        mBase.setAlpha(alpha);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int getAlpha() {
        return mBase.getAlpha();
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mBase.setColorFilter(colorFilter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public ColorFilter getColorFilter() {
        return mBase.getColorFilter();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setTintList(ColorStateList tint) {
        mBase.setTintList(tint);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setTintMode(PorterDuff.Mode tintMode) {
        mBase.setTintMode(tintMode);
    }

    @Override
    public Drawable mutate() {
        return mBase.mutate();
    }

    @Override
    public boolean isStateful() {
        return mBase.isStateful();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void inflate(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
        mBase.inflate(r, parser, attrs, theme);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void applyTheme(Resources.Theme t) {
        mBase.applyTheme(t);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean canApplyTheme() {
        return mBase.canApplyTheme();
    }

    @Override
    public int getIntrinsicWidth() {
        return mBase.getIntrinsicWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return mBase.getIntrinsicHeight();
    }

    @Override
    public int getOpacity() {
        return mBase.getOpacity();
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        mBase.setBounds(left, top, right, bottom);
    }

    @Override
    public void setBounds(@NonNull Rect bounds) {
        mBase.setBounds(bounds);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Rect getDirtyBounds() {
        return mBase.getDirtyBounds();
    }

    @Override
    public void setChangingConfigurations(int configs) {
        mBase.setChangingConfigurations(configs);
    }

    @Nullable
    @Override
    public Callback getCallback() {
        return mBase.getCallback();
    }

    @Override
    public void invalidateSelf() {
        mBase.invalidateSelf();
    }

    @Override
    public void scheduleSelf(@NonNull Runnable what, long when) {
        mBase.scheduleSelf(what, when);
    }

    @Override
    public void unscheduleSelf(@NonNull Runnable what) {
        mBase.unscheduleSelf(what);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int getLayoutDirection() {
        return mBase.getLayoutDirection();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onLayoutDirectionChanged(int layoutDirection) {
        return mBase.onLayoutDirectionChanged(layoutDirection);
    }

    @Override
    public void setColorFilter(int color, @NonNull PorterDuff.Mode mode) {
        mBase.setColorFilter(color, mode);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setTint(int tintColor) {
        mBase.setTint(tintColor);
    }

    @Override
    public void clearColorFilter() {
        mBase.clearColorFilter();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setHotspot(float x, float y) {
        mBase.setHotspot(x, y);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void setHotspotBounds(int left, int top, int right, int bottom) {
        mBase.setHotspotBounds(left, top, right, bottom);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void getHotspotBounds(@NonNull Rect outRect) {
        mBase.getHotspotBounds(outRect);
    }

    @Override
    public boolean setState(@NonNull int[] stateSet) {
        return mBase.setState(stateSet);
    }

    @NonNull
    @Override
    public int[] getState() {
        return mBase.getState();
    }

    @Override
    public void jumpToCurrentState() {
        mBase.jumpToCurrentState();
    }

    @NonNull
    @Override
    public Drawable getCurrent() {
        return mBase.getCurrent();
    }

    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        return mBase.setVisible(visible, restart);
    }

    @Nullable
    @Override
    public Region getTransparentRegion() {
        return mBase.getTransparentRegion();
    }

    @Override
    public int getMinimumWidth() {
        return mBase.getMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return mBase.getMinimumHeight();
    }

    @Override
    public boolean getPadding(@NonNull Rect padding) {
        return mBase.getPadding(padding);
    }

    @Override
    public void inflate(@NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs) throws XmlPullParserException, IOException {
        mBase.inflate(r, parser, attrs);
    }
}
