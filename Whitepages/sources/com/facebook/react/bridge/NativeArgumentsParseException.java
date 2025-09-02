package com.facebook.react.bridge;

import kotlin.jvm.internal.Intrinsics;

public final class NativeArgumentsParseException extends JSApplicationCausedNativeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NativeArgumentsParseException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NativeArgumentsParseException(String str, Throwable th) {
        super(str, th);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
    }
}
