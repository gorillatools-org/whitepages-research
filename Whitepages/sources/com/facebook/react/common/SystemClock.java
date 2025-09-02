package com.facebook.react.common;

public final class SystemClock {
    public static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
    }

    public static final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static final long nanoTime() {
        return System.nanoTime();
    }

    public static final long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
