package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.jvm.internal.Intrinsics;

@DoNotStrip
public final class ObjectAlreadyConsumedException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @DoNotStrip
    public ObjectAlreadyConsumedException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
    }
}
