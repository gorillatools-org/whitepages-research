package com.facebook.react.uimanager;

import kotlin.jvm.internal.Intrinsics;

public final class ReactInvalidPropertyException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReactInvalidPropertyException(String str, String str2, String str3) {
        super("Invalid React property `" + str + "` with value `" + str2 + "`, expected " + str3);
        Intrinsics.checkNotNullParameter(str, "property");
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(str3, "expectedValues");
    }
}
