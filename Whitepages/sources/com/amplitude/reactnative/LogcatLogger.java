package com.amplitude.reactnative;

import android.util.Log;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LogcatLogger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final LogcatLogger logger = new LogcatLogger();
    private Logger$LogMode logMode = Logger$LogMode.INFO;
    private final String tag = "Amplitude";

    public Logger$LogMode getLogMode() {
        return this.logMode;
    }

    public void error(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (getLogMode().compareTo(Logger$LogMode.ERROR) <= 0) {
            Log.e(this.tag, str);
        }
    }

    public void warn(String str) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        if (getLogMode().compareTo(Logger$LogMode.WARN) <= 0) {
            Log.w(this.tag, str);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LogcatLogger getLogger() {
            return LogcatLogger.logger;
        }
    }
}
