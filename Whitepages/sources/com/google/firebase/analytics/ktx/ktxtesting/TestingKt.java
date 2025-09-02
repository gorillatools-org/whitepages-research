package com.google.firebase.analytics.ktx.ktxtesting;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.ktx.AnalyticsKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

public final class TestingKt {
    /* JADX INFO: finally extract failed */
    public static final void withAnalyticsForTest(FirebaseAnalytics firebaseAnalytics, Function0 function0) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "analytics");
        Intrinsics.checkNotNullParameter(function0, "block");
        synchronized (AnalyticsKt.getLOCK()) {
            FirebaseAnalytics analytics = AnalyticsKt.getANALYTICS();
            AnalyticsKt.setANALYTICS(firebaseAnalytics);
            try {
                function0.invoke();
                AnalyticsKt.setANALYTICS(analytics);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                AnalyticsKt.setANALYTICS(analytics);
                throw th;
            }
        }
    }
}
