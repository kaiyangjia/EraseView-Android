package com.jiakaiyang.eraseview.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jia on 2017/9/30.
 */

public class EraseView extends View {
    private static final String TAG = "EraseView";

    public EraseView(Context context) {
        super(context);
    }

    public EraseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EraseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EraseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
