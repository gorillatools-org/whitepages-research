package com.facebook.internal;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

public final class Logger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final HashMap stringsToReplace = new HashMap();
    private final LoggingBehavior behavior;
    private StringBuilder contents;
    private int priority = 3;
    private final String tag;

    public Logger(LoggingBehavior loggingBehavior, String str) {
        Intrinsics.checkNotNullParameter(loggingBehavior, "behavior");
        Intrinsics.checkNotNullParameter(str, "tag");
        this.behavior = loggingBehavior;
        this.tag = "FacebookSDK." + Validate.notNullOrEmpty(str, "tag");
        this.contents = new StringBuilder();
    }

    public final void log() {
        String sb = this.contents.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "contents.toString()");
        logString(sb);
        this.contents = new StringBuilder();
    }

    public final void logString(String str) {
        Intrinsics.checkNotNullParameter(str, "string");
        Companion.log(this.behavior, this.priority, this.tag, str);
    }

    public final void append(String str) {
        Intrinsics.checkNotNullParameter(str, "string");
        if (shouldLog()) {
            this.contents.append(str);
        }
    }

    public final void append(String str, Object... objArr) {
        Intrinsics.checkNotNullParameter(str, "format");
        Intrinsics.checkNotNullParameter(objArr, "args");
        if (shouldLog()) {
            StringBuilder sb = this.contents;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
            String format = String.format(str, Arrays.copyOf(copyOf, copyOf.length));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            sb.append(format);
        }
    }

    public final void appendKeyValue(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(obj, "value");
        append("  %s:\t%s\n", str, obj);
    }

    private final boolean shouldLog() {
        return FacebookSdk.isLoggingBehaviorEnabled(this.behavior);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final synchronized void registerStringToReplace(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "original");
            Intrinsics.checkNotNullParameter(str2, "replace");
            Logger.stringsToReplace.put(str, str2);
        }

        public final synchronized void registerAccessToken(String str) {
            Intrinsics.checkNotNullParameter(str, "accessToken");
            if (!FacebookSdk.isLoggingBehaviorEnabled(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                registerStringToReplace(str, "ACCESS_TOKEN_REMOVED");
            }
        }

        public final void log(LoggingBehavior loggingBehavior, String str, String str2) {
            Intrinsics.checkNotNullParameter(loggingBehavior, "behavior");
            Intrinsics.checkNotNullParameter(str, "tag");
            Intrinsics.checkNotNullParameter(str2, "string");
            log(loggingBehavior, 3, str, str2);
        }

        public final void log(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
            Intrinsics.checkNotNullParameter(loggingBehavior, "behavior");
            Intrinsics.checkNotNullParameter(str, "tag");
            Intrinsics.checkNotNullParameter(str2, "format");
            Intrinsics.checkNotNullParameter(objArr, "args");
            if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
                String format = String.format(str2, Arrays.copyOf(copyOf, copyOf.length));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                log(loggingBehavior, 3, str, format);
            }
        }

        public final void log(LoggingBehavior loggingBehavior, int i, String str, String str2) {
            Intrinsics.checkNotNullParameter(loggingBehavior, "behavior");
            Intrinsics.checkNotNullParameter(str, "tag");
            Intrinsics.checkNotNullParameter(str2, "string");
            if (FacebookSdk.isLoggingBehaviorEnabled(loggingBehavior)) {
                String replaceStrings = replaceStrings(str2);
                if (!StringsKt.startsWith$default(str, "FacebookSDK.", false, 2, (Object) null)) {
                    str = "FacebookSDK." + str;
                }
                Log.println(i, str, replaceStrings);
                if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                    new Exception().printStackTrace();
                }
            }
        }

        private final synchronized String replaceStrings(String str) {
            String str2;
            str2 = str;
            for (Map.Entry entry : Logger.stringsToReplace.entrySet()) {
                str2 = StringsKt.replace$default(str2, (String) entry.getKey(), (String) entry.getValue(), false, 4, (Object) null);
            }
            return str2;
        }
    }
}
