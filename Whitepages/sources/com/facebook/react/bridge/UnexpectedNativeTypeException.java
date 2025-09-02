package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class UnexpectedNativeTypeException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnexpectedNativeTypeException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "msg");
    }
}
