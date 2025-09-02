package com.facebook.systrace;

import androidx.tracing.Trace;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public final class Systrace {
    public static final Systrace INSTANCE = new Systrace();

    public static final boolean isTracing(long j) {
        return false;
    }

    public static final void registerListener(TraceListener traceListener) {
    }

    public static final void traceInstant(long j, String str, EventScope eventScope) {
    }

    public static final void unregisterListener(TraceListener traceListener) {
    }

    private Systrace() {
    }

    public static final void traceSection(long j, String str, Runnable runnable) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Intrinsics.checkNotNullParameter(runnable, "block");
        beginSection(j, str);
        try {
            runnable.run();
        } finally {
            endSection(j);
        }
    }

    public static final void beginSection(long j, String str) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Trace.beginSection(str);
    }

    public static final void beginSection(long j, String str, String[] strArr, int i) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Intrinsics.checkNotNullParameter(strArr, "args");
        String convertArgsToText = INSTANCE.convertArgsToText(strArr, i);
        Trace.beginSection(str + "|" + convertArgsToText);
    }

    private final String convertArgsToText(String[] strArr, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 1; i2 < i; i2 += 2) {
            String str = strArr[i2 - 1];
            String str2 = strArr[i2];
            sb.append(str);
            sb.append('=');
            sb.append(str2);
            if (i2 < i - 1) {
                sb.append(';');
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    public static final void endSection(long j) {
        Trace.endSection();
    }

    public static final void beginAsyncSection(long j, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Trace.beginAsyncSection(str, i);
    }

    public static final void beginAsyncSection(long j, String str, int i, long j2) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        beginAsyncSection(j, str, i);
    }

    public static final void endAsyncSection(long j, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        Trace.endAsyncSection(str, i);
    }

    public static final void endAsyncSection(long j, String str, int i, long j2) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        endAsyncSection(j, str, i);
    }

    public static final void traceCounter(long j, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "counterName");
        Trace.setCounter(str, i);
    }

    public static final void startAsyncFlow(long j, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        beginAsyncSection(j, str, i);
    }

    public static final void endAsyncFlow(long j, String str, int i) {
        Intrinsics.checkNotNullParameter(str, "sectionName");
        endAsyncSection(j, str, i);
    }

    public enum EventScope {
        THREAD('t'),
        PROCESS('p'),
        GLOBAL('g');
        
        private final char code;

        private EventScope(char c) {
            this.code = c;
        }

        static {
            EventScope[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries($values);
        }
    }
}
