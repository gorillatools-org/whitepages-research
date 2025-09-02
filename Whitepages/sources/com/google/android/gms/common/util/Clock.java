package com.google.android.gms.common.util;

import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;

@ShowFirstParty
@KeepForSdk
public interface Clock {

    /* renamed from: com.google.android.gms.common.util.Clock$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        @KeepForSdk
        public static long $default$currentThreadTimeMillis(Clock clock) {
            return SystemClock.currentThreadTimeMillis();
        }
    }

    @KeepForSdk
    long currentThreadTimeMillis();

    @KeepForSdk
    long currentTimeMillis();

    @KeepForSdk
    long elapsedRealtime();

    @KeepForSdk
    long nanoTime();
}
