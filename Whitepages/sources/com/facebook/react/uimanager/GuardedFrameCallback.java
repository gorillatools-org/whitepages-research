package com.facebook.react.uimanager;

import android.view.Choreographer;
import com.facebook.react.bridge.JSExceptionHandler;
import kotlin.jvm.internal.Intrinsics;

public abstract class GuardedFrameCallback implements Choreographer.FrameCallback {
    private final JSExceptionHandler exceptionHandler;

    /* access modifiers changed from: protected */
    public abstract void doFrameGuarded(long j);

    protected GuardedFrameCallback(JSExceptionHandler jSExceptionHandler) {
        Intrinsics.checkNotNullParameter(jSExceptionHandler, "exceptionHandler");
        this.exceptionHandler = jSExceptionHandler;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected GuardedFrameCallback(com.facebook.react.bridge.ReactContext r2) {
        /*
            r1 = this;
            java.lang.String r0 = "reactContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            com.facebook.react.bridge.JSExceptionHandler r2 = r2.getExceptionHandler()
            java.lang.String r0 = "getExceptionHandler(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            r1.<init>((com.facebook.react.bridge.JSExceptionHandler) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.GuardedFrameCallback.<init>(com.facebook.react.bridge.ReactContext):void");
    }

    public void doFrame(long j) {
        try {
            doFrameGuarded(j);
        } catch (RuntimeException e) {
            this.exceptionHandler.handleException(e);
        }
    }
}
