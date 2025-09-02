package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;

public interface MonotonicNanoClock extends MonotonicClock {
    @DoNotStrip
    /* bridge */ /* synthetic */ long now() {
        return super.now();
    }

    @DoNotStrip
    /* synthetic */ long nowNanos();
}
