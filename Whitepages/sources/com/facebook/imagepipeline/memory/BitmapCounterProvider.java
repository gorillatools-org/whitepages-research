package com.facebook.imagepipeline.memory;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class BitmapCounterProvider {
    public static final BitmapCounterProvider INSTANCE;
    public static final int MAX_BITMAP_TOTAL_SIZE;
    private static volatile BitmapCounter bitmapCounter;
    private static int maxBitmapCount = 384;

    private BitmapCounterProvider() {
    }

    static {
        BitmapCounterProvider bitmapCounterProvider = new BitmapCounterProvider();
        INSTANCE = bitmapCounterProvider;
        MAX_BITMAP_TOTAL_SIZE = bitmapCounterProvider.getMaxSizeHardCap();
    }

    private final int getMaxSizeHardCap() {
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (((long) min) > 16777216) {
            return (min / 4) * 3;
        }
        return min / 2;
    }

    public static final BitmapCounter get() {
        if (bitmapCounter == null) {
            synchronized (BitmapCounterProvider.class) {
                try {
                    if (bitmapCounter == null) {
                        bitmapCounter = new BitmapCounter(maxBitmapCount, MAX_BITMAP_TOTAL_SIZE);
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        BitmapCounter bitmapCounter2 = bitmapCounter;
        Intrinsics.checkNotNull(bitmapCounter2);
        return bitmapCounter2;
    }
}
