package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;

@DoNotStrip
public class AwakeTimeSinceBootClock implements MonotonicNanoClock {
    @DoNotStrip
    private static final AwakeTimeSinceBootClock INSTANCE = new AwakeTimeSinceBootClock();

    @DoNotStrip
    public /* bridge */ /* synthetic */ long now() {
        return super.now();
    }

    private AwakeTimeSinceBootClock() {
    }

    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return INSTANCE;
    }

    @DoNotStrip
    public long nowNanos() {
        return System.nanoTime();
    }
}
