package com.google.firebase.perf;

import com.google.firebase.Firebase;
import com.google.firebase.perf.metrics.HttpMetric;
import com.google.firebase.perf.metrics.Trace;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PerformanceKt {
    public static final FirebasePerformance getPerformance(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebasePerformance instance = FirebasePerformance.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final void trace(HttpMetric httpMetric, Function1 function1) {
        Intrinsics.checkNotNullParameter(httpMetric, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        httpMetric.start();
        try {
            function1.invoke(httpMetric);
        } finally {
            InlineMarker.finallyStart(1);
            httpMetric.stop();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T trace(Trace trace, Function1 function1) {
        Intrinsics.checkNotNullParameter(trace, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        trace.start();
        try {
            return function1.invoke(trace);
        } finally {
            InlineMarker.finallyStart(1);
            trace.stop();
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T trace(String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, "block");
        Trace create = Trace.create(str);
        Intrinsics.checkNotNullExpressionValue(create, "create(name)");
        create.start();
        try {
            return function1.invoke(create);
        } finally {
            InlineMarker.finallyStart(1);
            create.stop();
            InlineMarker.finallyEnd(1);
        }
    }
}
