package com.facebook.react.bridge;

import kotlin.jvm.internal.Intrinsics;

public final class JSApplicationIllegalArgumentException extends JSApplicationCausedNativeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSApplicationIllegalArgumentException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSApplicationIllegalArgumentException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
        Intrinsics.checkNotNullParameter(th, "t");
    }
}
