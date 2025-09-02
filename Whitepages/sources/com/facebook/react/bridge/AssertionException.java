package com.facebook.react.bridge;

import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public final class AssertionException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AssertionException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
    }
}
