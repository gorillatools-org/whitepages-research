package com.facebook.internal.instrument.crashshield;

public final class CrashShieldHandler$scheduleCrashInDebug$1 implements Runnable {
    final /* synthetic */ Throwable $e;

    CrashShieldHandler$scheduleCrashInDebug$1(Throwable th) {
        this.$e = th;
    }

    public void run() {
        throw new RuntimeException(this.$e);
    }
}
