package com.facebook.react.bridge;

import kotlin.jvm.internal.Intrinsics;

public class ReactNoCrashSoftException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashSoftException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "m");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashSoftException(Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(th, "e");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactNoCrashSoftException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "m");
    }
}
