package com.facebook.react.uimanager;

import kotlin.jvm.internal.Intrinsics;

public final class NoSuchNativeViewException extends IllegalViewOperationException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NoSuchNativeViewException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "detailMessage");
    }
}
