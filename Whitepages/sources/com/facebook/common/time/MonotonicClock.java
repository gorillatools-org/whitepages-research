package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;
import java.util.concurrent.TimeUnit;

public interface MonotonicClock {
    @DoNotStrip
    long nowNanos();

    @DoNotStrip
    long now() {
        return TimeUnit.NANOSECONDS.toMillis(nowNanos());
    }
}
