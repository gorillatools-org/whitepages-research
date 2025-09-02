package com.facebook.debug.holder;

import com.facebook.debug.debugoverlay.model.DebugOverlayTag;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public final class NoopPrinter implements Printer {
    public static final NoopPrinter INSTANCE = new NoopPrinter();

    public void logMessage(DebugOverlayTag debugOverlayTag, String str) {
        Intrinsics.checkNotNullParameter(debugOverlayTag, "tag");
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
    }

    public void logMessage(DebugOverlayTag debugOverlayTag, String str, Object... objArr) {
        Intrinsics.checkNotNullParameter(debugOverlayTag, "tag");
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        Intrinsics.checkNotNullParameter(objArr, "args");
    }

    public boolean shouldDisplayLogMessage(DebugOverlayTag debugOverlayTag) {
        Intrinsics.checkNotNullParameter(debugOverlayTag, "tag");
        return false;
    }

    private NoopPrinter() {
    }
}
