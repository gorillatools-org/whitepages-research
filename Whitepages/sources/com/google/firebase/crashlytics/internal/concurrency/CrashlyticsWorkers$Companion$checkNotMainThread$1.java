package com.google.firebase.crashlytics.internal.concurrency;

import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsWorkers;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* synthetic */ class CrashlyticsWorkers$Companion$checkNotMainThread$1 extends FunctionReferenceImpl implements Function0 {
    CrashlyticsWorkers$Companion$checkNotMainThread$1(Object obj) {
        super(0, obj, CrashlyticsWorkers.Companion.class, "isNotMainThread", "isNotMainThread()Z", 0);
    }

    public final Boolean invoke() {
        return Boolean.valueOf(((CrashlyticsWorkers.Companion) this.receiver).isNotMainThread());
    }
}
