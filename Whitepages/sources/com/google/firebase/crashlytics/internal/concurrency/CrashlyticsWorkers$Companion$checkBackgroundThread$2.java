package com.google.firebase.crashlytics.internal.concurrency;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class CrashlyticsWorkers$Companion$checkBackgroundThread$2 extends Lambda implements Function0 {
    public static final CrashlyticsWorkers$Companion$checkBackgroundThread$2 INSTANCE = new CrashlyticsWorkers$Companion$checkBackgroundThread$2();

    CrashlyticsWorkers$Companion$checkBackgroundThread$2() {
        super(0);
    }

    public final String invoke() {
        return "Must be called on a background thread, was called on " + CrashlyticsWorkers.Companion.getThreadName() + '.';
    }
}
