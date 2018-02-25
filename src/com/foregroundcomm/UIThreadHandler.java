package com.foregroundcomm;

import android.os.Handler;
import android.os.Looper;

public class UIThreadHandler {
    private Handler mHandler;
    private static UIThreadHandler mInstance;

    static {
        mInstance = new UIThreadHandler();
    }

    private UIThreadHandler() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static void post(Runnable runnable) {
        if(runnable != null)
            mInstance.mHandler.post(runnable);
    }
}
