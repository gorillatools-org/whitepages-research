package com.facebook.react.bridge;

import kotlin.jvm.internal.Intrinsics;

public final class ReactNoCrashBridgeNotAllowedSoftException extends ReactNoCrashSoftException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashBridgeNotAllowedSoftException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "m");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashBridgeNotAllowedSoftException(Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(th, "e");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashBridgeNotAllowedSoftException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "m");
        Intrinsics.checkNotNullParameter(th, "e");
    }
}
