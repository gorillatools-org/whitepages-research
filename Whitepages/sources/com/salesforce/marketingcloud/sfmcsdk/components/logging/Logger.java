package com.salesforce.marketingcloud.sfmcsdk.components.logging;

import android.text.TextUtils;
import android.util.Log;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

public abstract class Logger {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_TAG_LENGTH = 23;
    private static final String REDACTED_VALUE_REPLACEMENT_TEXT = "[REDACTED]";
    private LogListener listener;
    private LogLevel logLevel = LogLevel.ERROR;
    private List<String> redactedValues = CollectionsKt.emptyList();

    public List<String> getRedactedValues() {
        return this.redactedValues;
    }

    public void setRedactedValues(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.redactedValues = list;
    }

    public LogLevel getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(LogLevel logLevel2) {
        Intrinsics.checkNotNullParameter(logLevel2, "<set-?>");
        this.logLevel = logLevel2;
    }

    public LogListener getListener() {
        return this.listener;
    }

    public void setListener(LogListener logListener) {
        this.listener = logListener;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final String createTag(KClass kClass) {
        Intrinsics.checkNotNullParameter(kClass, "clazz");
        String simpleName = JvmClassMappingKt.getJavaClass(kClass).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "clazz.java.simpleName");
        return createTag(simpleName);
    }

    public final String createTag(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return formatTag(str);
    }

    public void d(String str, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        d(str, (Throwable) null, function0);
    }

    public static /* synthetic */ void d$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                th = null;
            }
            logger.d(str, th, function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: d");
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

    public static /* synthetic */ void w$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                th = null;
            }
            logger.w(str, th, function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: w");
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

    public static /* synthetic */ void e$default(Logger logger, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                th = null;
            }
            logger.e(str, th, function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: e");
    }

    public void e(String str, Throwable th, Function0 function0) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        log$sfmcsdk_release(LogLevel.ERROR, str, th, function0);
    }

    public static /* synthetic */ void log$sfmcsdk_release$default(Logger logger, LogLevel logLevel2, String str, Throwable th, Function0 function0, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                th = null;
            }
            logger.log$sfmcsdk_release(logLevel2, str, th, function0);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
    }

    public final void log$sfmcsdk_release(LogLevel logLevel2, String str, Throwable th, Function0 function0) {
        Intrinsics.checkNotNullParameter(logLevel2, "lvl");
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(function0, "lazyMsg");
        LogListener listener2 = getListener();
        if (listener2 != null && logLevel2.compareTo(getLogLevel()) >= 0) {
            try {
                listener2.out(logLevel2, formatTag(str), formatMsg((String) function0.invoke()), th);
            } catch (Exception e) {
                Log.e("~$Logger", "Exception was thrown by " + listener2.getClass().getName(), e);
            }
        }
    }

    private final String formatTag(String str) {
        return str.length() <= 23 ? str : str.subSequence(0, 23).toString();
    }

    private final String formatMsg(String str) {
        if (TextUtils.getTrimmedLength(str) == 0) {
            return "FORMATTED LOG MESSAGE WAS EMPTY";
        }
        for (String str2 : getRedactedValues()) {
            if (!Intrinsics.areEqual((Object) REDACTED_VALUE_REPLACEMENT_TEXT, (Object) str2)) {
                str = StringsKt.replace(str, str2, REDACTED_VALUE_REPLACEMENT_TEXT, true);
            }
        }
        return str;
    }
}
