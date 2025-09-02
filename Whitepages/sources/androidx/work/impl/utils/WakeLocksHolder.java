package androidx.work.impl.utils;

import java.util.WeakHashMap;

final class WakeLocksHolder {
    public static final WakeLocksHolder INSTANCE = new WakeLocksHolder();
    private static final WeakHashMap wakeLocks = new WeakHashMap();

    private WakeLocksHolder() {
    }

    public final WeakHashMap getWakeLocks() {
        return wakeLocks;
    }
}
