package com.jiakaiyang.eraseview.lib;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jia on 2017/10/3.
 * start a thread and check the input args.
 */

public class CheckNullThread<T> extends Thread {
    private static final String TAG = "CheckNullThread";

    private static final long SLEEP_TIME = 300;

    private T object;
    private T target;

    private Set<OnChangeListener> onChangeListeners;

    public CheckNullThread(T object, T target) {
        this.object = object;
        this.target = target;
    }

    @Override
    public void run() {
        super.run();
        startLoopCheck();
    }

    private void startLoopCheck() {
        boolean isEqual = object == target;

        while (true) {
            boolean isEqualNew = object == target;

            try {
                if (isEqualNew == isEqual) {
                    sleep(SLEEP_TIME);
                } else {
                    if (onChangeListeners != null) {
                        for (OnChangeListener listener : onChangeListeners) {
                            listener.onChange(object, target);
                        }
                    }
                    break;
                }
            } catch (InterruptedException e) {
                Log.e(TAG, "startLoopCheck: InterruptedException:  " + e.getMessage());
            }
        }
    }

    public boolean addOnChangeListener(OnChangeListener listener) {
        if (listener == null) {
            return false;
        }

        if (onChangeListeners == null) {
            onChangeListeners = new HashSet<>();
        }

        boolean result = onChangeListeners.add(listener);
        if (!result) {
            Log.w(TAG, "addOnChangeListener: add listener failure");
        }

        return result;
    }

    public boolean removeOnChangeListener(OnChangeListener listener) {
        if (onChangeListeners == null
                || listener == null) {
            Log.w(TAG, "removeOnChangeListener: listener or onChangeListeners is null return");
            return false;
        }

        boolean result = onChangeListeners.remove(listener);
        if (!result) {
            Log.w(TAG, "removeOnChangeListener: remove listener failure");
        }

        return result;
    }

    public interface OnChangeListener<T> {
        public void onChange(T object, T target);
    }
}
