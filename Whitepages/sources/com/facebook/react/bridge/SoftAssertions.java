package com.facebook.react.bridge;

import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public final class SoftAssertions {
    public static final SoftAssertions INSTANCE = new SoftAssertions();

    private SoftAssertions() {
    }

    public static final void assertUnreachable(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.SOFT_ASSERTIONS, new AssertionException(str));
    }

    public static final void assertCondition(boolean z, String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (!z) {
            ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.SOFT_ASSERTIONS, new AssertionException(str));
        }
    }

    public static final <T> T assertNotNull(T t) {
        if (t == null) {
            ReactSoftExceptionLogger.logSoftException(ReactSoftExceptionLogger.Categories.SOFT_ASSERTIONS, new AssertionException("Expected object to not be null!"));
        }
        return t;
    }
}
