package com.facebook.react.runtime;

import com.facebook.jni.HybridData;
import com.facebook.jni.annotations.DoNotStripAny;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.modules.core.JavaScriptTimerExecutor;
import com.facebook.soloader.SoLoader;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
public final class JSTimerExecutor implements JavaScriptTimerExecutor {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HybridData mHybridData;

    private final native void callTimers(WritableNativeArray writableNativeArray);

    public void callIdleCallbacks(double d) {
    }

    public void emitTimeDriftWarning(String str) {
        Intrinsics.checkNotNullParameter(str, "warningMessage");
    }

    public JSTimerExecutor(HybridData hybridData) {
        Intrinsics.checkNotNullParameter(hybridData, "mHybridData");
        this.mHybridData = hybridData;
    }

    public void callTimers(WritableArray writableArray) {
        Intrinsics.checkNotNullParameter(writableArray, "timerIDs");
        callTimers((WritableNativeArray) writableArray);
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SoLoader.loadLibrary("rninstance");
    }
}
