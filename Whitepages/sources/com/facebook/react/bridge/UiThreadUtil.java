package com.facebook.react.bridge;

import android.os.Handler;
import android.os.Looper;
import com.facebook.react.common.build.ReactBuildConfig;

public class UiThreadUtil {
    private static volatile Handler sMainHandler;

    public static Handler getUiThreadHandler() {
        if (sMainHandler == null) {
            synchronized (UiThreadUtil.class) {
                try {
                    if (sMainHandler == null) {
                        sMainHandler = new Handler(Looper.getMainLooper());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sMainHandler;
    }

    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void assertOnUiThread() {
        if (ReactBuildConfig.DEBUG) {
            SoftAssertions.assertCondition(isOnUiThread(), "Expected to run on UI thread!");
        }
    }

    public static void assertNotOnUiThread() {
        if (ReactBuildConfig.DEBUG) {
            SoftAssertions.assertCondition(!isOnUiThread(), "Expected not to run on UI thread!");
        }
    }

    public static boolean runOnUiThread(Runnable runnable) {
        return getUiThreadHandler().postDelayed(runnable, 0);
    }

    public static boolean runOnUiThread(Runnable runnable, long j) {
        return getUiThreadHandler().postDelayed(runnable, j);
    }

    public static void removeOnUiThread(Runnable runnable) {
        getUiThreadHandler().removeCallbacks(runnable);
    }
}
