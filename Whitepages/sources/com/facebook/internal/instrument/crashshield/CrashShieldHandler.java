package com.facebook.internal.instrument.crashshield;

import android.os.Handler;
import android.os.Looper;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.ExceptionAnalyzer;
import com.facebook.internal.instrument.InstrumentData;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.jvm.internal.Intrinsics;

public final class CrashShieldHandler {
    public static final CrashShieldHandler INSTANCE = new CrashShieldHandler();
    private static final Set crashingObjects = Collections.newSetFromMap(new WeakHashMap());
    private static boolean enabled;

    public static final boolean isDebug() {
        return false;
    }

    private CrashShieldHandler() {
    }

    public static final void enable() {
        enabled = true;
    }

    public static final void handleThrowable(Throwable th, Object obj) {
        Intrinsics.checkNotNullParameter(obj, "o");
        if (enabled) {
            crashingObjects.add(obj);
            if (FacebookSdk.getAutoLogAppEventsEnabled()) {
                ExceptionAnalyzer.execute(th);
                InstrumentData.Builder.build(th, InstrumentData.Type.CrashShield).save();
            }
            scheduleCrashInDebug(th);
        }
    }

    public static final boolean isObjectCrashing(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "o");
        return crashingObjects.contains(obj);
    }

    public static final void scheduleCrashInDebug(Throwable th) {
        if (isDebug()) {
            new Handler(Looper.getMainLooper()).post(new CrashShieldHandler$scheduleCrashInDebug$1(th));
        }
    }
}
