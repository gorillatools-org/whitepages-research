package com.facebook.react.common;

import java.util.concurrent.TimeUnit;

public final class ShakeDetectorKt {
    /* access modifiers changed from: private */
    public static final long MIN_TIME_BETWEEN_SAMPLES_NS;
    private static final float REQUIRED_FORCE = 13.042845f;
    /* access modifiers changed from: private */
    public static final float SHAKING_WINDOW_NS;

    static {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        MIN_TIME_BETWEEN_SAMPLES_NS = timeUnit.convert(20, TimeUnit.MILLISECONDS);
        SHAKING_WINDOW_NS = (float) timeUnit.convert(3, TimeUnit.SECONDS);
    }
}
