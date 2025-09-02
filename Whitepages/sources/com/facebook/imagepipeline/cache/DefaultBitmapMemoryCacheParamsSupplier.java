package com.facebook.imagepipeline.cache;

import android.app.ActivityManager;
import com.facebook.common.internal.Supplier;
import java.util.concurrent.TimeUnit;

public class DefaultBitmapMemoryCacheParamsSupplier implements Supplier {
    private static final long PARAMS_CHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    private final ActivityManager mActivityManager;

    public DefaultBitmapMemoryCacheParamsSupplier(ActivityManager activityManager) {
        this.mActivityManager = activityManager;
    }

    public MemoryCacheParams get() {
        return new MemoryCacheParams(getMaxCacheSize(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, PARAMS_CHECK_INTERVAL_MS);
    }

    private int getMaxCacheSize() {
        int min = Math.min(this.mActivityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        return min / 4;
    }
}
