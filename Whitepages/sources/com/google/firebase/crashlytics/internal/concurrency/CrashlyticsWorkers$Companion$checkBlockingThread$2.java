package com.google.firebase.crashlytics.internal.concurrency;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class CrashlyticsWorkers$Companion$checkBlockingThread$2 extends Lambda implements Function0 {
    public static final CrashlyticsWorkers$Companion$checkBlockingThread$2 INSTANCE = new CrashlyticsWorkers$Companion$checkBlockingThread$2();

    CrashlyticsWorkers$Companion$checkBlockingThread$2() {
        super(0);
    }

    public final String invoke() {
        return "Must be called on a blocking thread, was called on " + CrashlyticsWorkers.Companion.getThreadName() + '.';
    }
}
