package com.facebook.react.bridge;

import kotlin.jvm.internal.Intrinsics;

public final class RetryableMountingLayerException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RetryableMountingLayerException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "msg");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RetryableMountingLayerException(Throwable th) {
        super(th);
        Intrinsics.checkNotNullParameter(th, "e");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RetryableMountingLayerException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "msg");
    }
}
