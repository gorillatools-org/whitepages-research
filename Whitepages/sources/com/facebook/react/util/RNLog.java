package com.facebook.react.util;

import app.notifee.core.event.LogEvent;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.Intrinsics;

public final class RNLog {
    public static final int ADVICE = 4;
    public static final int ERROR = 6;
    public static final RNLog INSTANCE = new RNLog();
    public static final int LOG = 2;
    public static final int MINIMUM_LEVEL_FOR_UI = 5;
    public static final int TRACE = 3;
    public static final int WARN = 5;

    private RNLog() {
    }

    public static final void l(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        FLog.i(ReactConstants.TAG, str);
    }

    public static final void t(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        FLog.i(ReactConstants.TAG, str);
    }

    public static final void a(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        FLog.w(ReactConstants.TAG, "(ADVICE)" + str);
    }

    public static final void w(ReactContext reactContext, String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        INSTANCE.logInternal(reactContext, str, 5);
        FLog.w(ReactConstants.TAG, str);
    }

    public static final void e(ReactContext reactContext, String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        INSTANCE.logInternal(reactContext, str, 6);
        FLog.e(ReactConstants.TAG, str);
    }

    public static final void e(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        FLog.e(ReactConstants.TAG, str);
    }

    private final void logInternal(ReactContext reactContext, String str, int i) {
        if (i >= 5 && reactContext != null && reactContext.hasActiveReactInstance() && str != null) {
            ((RCTLog) reactContext.getJSModule(RCTLog.class)).logIfNoNativeHook(levelToString(i), str);
        }
    }

    private final String levelToString(int i) {
        if (i == 2 || i == 3) {
            return "log";
        }
        if (i == 4 || i == 5) {
            return LogEvent.LEVEL_WARN;
        }
        if (i != 6) {
            return "none";
        }
        return "error";
    }
}
