package com.facebook.appevents.gps.topics;

import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

public final class GpsTopicsManager {
    public static final GpsTopicsManager INSTANCE = new GpsTopicsManager();
    private static final String TAG;
    private static final Lazy executor$delegate = LazyKt.lazy(GpsTopicsManager$executor$2.INSTANCE);
    private static final AtomicBoolean isTopicsObservationEnabled = new AtomicBoolean(false);

    private GpsTopicsManager() {
    }

    static {
        String cls = GpsTopicsManager.class.toString();
        Intrinsics.checkNotNullExpressionValue(cls, "GpsTopicsManager::class.java.toString()");
        TAG = cls;
    }

    public static final void enableTopicsObservation() {
        Class<GpsTopicsManager> cls = GpsTopicsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isTopicsObservationEnabled.set(true);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
