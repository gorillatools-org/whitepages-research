package com.google.firebase.perf.util;

import java.util.concurrent.TimeUnit;

public class Rate {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long numTimeUnits;
    private long numTokensPerTotalTimeUnit;
    private TimeUnit timeUnit;

    public Rate(long j, long j2, TimeUnit timeUnit2) {
        this.numTokensPerTotalTimeUnit = j;
        this.numTimeUnits = j2;
        this.timeUnit = timeUnit2;
    }

    /* renamed from: com.google.firebase.perf.util.Rate$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                java.util.concurrent.TimeUnit[] r0 = java.util.concurrent.TimeUnit.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$java$util$concurrent$TimeUnit = r0
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x001d }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MICROSECONDS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$java$util$concurrent$TimeUnit     // Catch:{ NoSuchFieldError -> 0x0028 }
                java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.perf.util.Rate.AnonymousClass1.<clinit>():void");
        }
    }

    public double getTokensPerSeconds() {
        int i = AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[this.timeUnit.ordinal()];
        if (i == 1) {
            return (((double) this.numTokensPerTotalTimeUnit) / ((double) this.numTimeUnits)) * ((double) TimeUnit.SECONDS.toNanos(1));
        }
        if (i == 2) {
            return (((double) this.numTokensPerTotalTimeUnit) / ((double) this.numTimeUnits)) * ((double) TimeUnit.SECONDS.toMicros(1));
        }
        if (i != 3) {
            return ((double) this.numTokensPerTotalTimeUnit) / ((double) this.timeUnit.toSeconds(this.numTimeUnits));
        }
        return (((double) this.numTokensPerTotalTimeUnit) / ((double) this.numTimeUnits)) * ((double) TimeUnit.SECONDS.toMillis(1));
    }
}
