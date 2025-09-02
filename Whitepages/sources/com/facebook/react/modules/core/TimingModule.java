package com.facebook.react.modules.core;

import com.facebook.fbreact.specs.NativeTimingSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "Timing")
public final class TimingModule extends NativeTimingSpec implements JavaScriptTimerExecutor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "Timing";
    private final JavaTimerManager javaTimerManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TimingModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        this.javaTimerManager = new JavaTimerManager(reactApplicationContext, this, ReactChoreographer.Companion.getInstance(), devSupportManager);
    }

    public void createTimer(double d, double d2, double d3, boolean z) {
        this.javaTimerManager.createAndMaybeCallTimer((int) d, (int) d2, d3, z);
    }

    public void deleteTimer(double d) {
        this.javaTimerManager.deleteTimer((int) d);
    }

    public void setSendIdleEvents(boolean z) {
        this.javaTimerManager.setSendIdleEvents(z);
    }

    public void callTimers(WritableArray writableArray) {
        JSTimers jSTimers;
        Intrinsics.checkNotNullParameter(writableArray, "timerIDs");
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null && (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) != null) {
            jSTimers.callTimers(writableArray);
        }
    }

    public void callIdleCallbacks(double d) {
        JSTimers jSTimers;
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null && (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) != null) {
            jSTimers.callIdleCallbacks(d);
        }
    }

    public void emitTimeDriftWarning(String str) {
        JSTimers jSTimers;
        Intrinsics.checkNotNullParameter(str, "warningMessage");
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null && (jSTimers = (JSTimers) reactApplicationContextIfActiveOrWarn.getJSModule(JSTimers.class)) != null) {
            jSTimers.emitTimeDriftWarning(str);
        }
    }

    public void invalidate() {
        this.javaTimerManager.onInstanceDestroy();
    }

    @VisibleForTesting
    public final boolean hasActiveTimersInRange(long j) {
        return this.javaTimerManager.hasActiveTimersInRange$ReactAndroid_release(j);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
