package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;
import java.util.concurrent.TimeUnit;

@DoNotStrip
public class RealtimeSinceBootClock implements MonotonicClock {
    private static final RealtimeSinceBootClock INSTANCE = new RealtimeSinceBootClock();

    private RealtimeSinceBootClock() {
    }

    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return INSTANCE;
    }

    public long now() {
        return SystemClock.elapsedRealtime();
    }

    public long nowNanos() {
        return TimeUnit.MILLISECONDS.toNanos(now());
    }
}
