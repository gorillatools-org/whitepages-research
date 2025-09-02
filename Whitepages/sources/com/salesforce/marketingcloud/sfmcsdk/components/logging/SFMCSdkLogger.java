package com.salesforce.marketingcloud.sfmcsdk.components.logging;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class SFMCSdkLogger extends Logger {
    public static final SFMCSdkLogger INSTANCE = new SFMCSdkLogger();

    private SFMCSdkLogger() {
    }

    public void d(String str, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        d(str, (Throwable) null, function0);
    }

    public void d(String str, Throwable th, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        log$sfmcsdk_release(LogLevel.DEBUG, str, th, function0);
    }

    public void w(String str, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        w(str, (Throwable) null, function0);
    }

    public void w(String str, Throwable th, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        log$sfmcsdk_release(LogLevel.WARN, str, th, function0);
    }

    public void e(String str, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        e(str, (Throwable) null, function0);
    }

    public void e(String str, Throwable th, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        log$sfmcsdk_release(LogLevel.ERROR, str, th, function0);
    }
}
