package com.google.firebase.crashlytics.internal.concurrency;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

final class CrashlyticsWorkers$Companion$checkNotMainThread$2 extends Lambda implements Function0 {
    public static final CrashlyticsWorkers$Companion$checkNotMainThread$2 INSTANCE = new CrashlyticsWorkers$Companion$checkNotMainThread$2();

    CrashlyticsWorkers$Companion$checkNotMainThread$2() {
        super(0);
    }

    public final String invoke() {
        return "Must not be called on a main thread, was called on " + CrashlyticsWorkers.Companion.getThreadName() + '.';
    }
}
