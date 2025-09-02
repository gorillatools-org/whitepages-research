package com.facebook.drawee.components;

import android.os.Looper;

public abstract class DeferredReleaser {
    private static DeferredReleaser sInstance;

    public interface Releasable {
        void release();
    }

    public abstract void cancelDeferredRelease(Releasable releasable);

    public abstract void scheduleDeferredRelease(Releasable releasable);

    public static synchronized DeferredReleaser getInstance() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            try {
                if (sInstance == null) {
                    sInstance = new DeferredReleaserConcurrentImpl();
                }
                deferredReleaser = sInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return deferredReleaser;
    }

    static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
