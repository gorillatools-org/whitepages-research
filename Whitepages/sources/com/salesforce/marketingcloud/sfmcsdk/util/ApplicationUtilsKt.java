package com.salesforce.marketingcloud.sfmcsdk.util;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class ApplicationUtilsKt {
    public static final <R> R orElse(R r, Function0 function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        return r == null ? function0.invoke() : r;
    }
}
