package com.google.firebase.sessions;

import android.os.SystemClock;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

public final class WallClock implements TimeProvider {
    public static final WallClock INSTANCE = new WallClock();
    private static final long US_PER_MILLIS = 1000;

    private WallClock() {
    }

    /* renamed from: elapsedRealtime-UwyO8pc  reason: not valid java name */
    public long m594elapsedRealtimeUwyO8pc() {
        Duration.Companion companion = Duration.Companion;
        return DurationKt.toDuration(SystemClock.elapsedRealtime(), DurationUnit.MILLISECONDS);
    }

    public long currentTimeUs() {
        return System.currentTimeMillis() * US_PER_MILLIS;
    }
}
