package com.google.firebase.analytics;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class AnalyticsKt {
    private static volatile FirebaseAnalytics zza;
    private static final Object zzb = new Object();

    public static final FirebaseAnalytics getANALYTICS() {
        return zza;
    }

    public static final FirebaseAnalytics getAnalytics(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        if (zza == null) {
            synchronized (zzb) {
                try {
                    if (zza == null) {
                        zza = FirebaseAnalytics.getInstance(FirebaseKt.getApp(Firebase.INSTANCE).getApplicationContext());
                    }
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        FirebaseAnalytics firebaseAnalytics = zza;
        Intrinsics.checkNotNull(firebaseAnalytics);
        return firebaseAnalytics;
    }

    public static final Object getLOCK() {
        return zzb;
    }

    public static final void setANALYTICS(FirebaseAnalytics firebaseAnalytics) {
        zza = firebaseAnalytics;
    }

    public static final void logEvent(FirebaseAnalytics firebaseAnalytics, String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, "block");
        ParametersBuilder parametersBuilder = new ParametersBuilder();
        function1.invoke(parametersBuilder);
        firebaseAnalytics.logEvent(str, parametersBuilder.getBundle());
    }

    public static final void setConsent(FirebaseAnalytics firebaseAnalytics, Function1 function1) {
        Intrinsics.checkNotNullParameter(firebaseAnalytics, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        ConsentBuilder consentBuilder = new ConsentBuilder();
        function1.invoke(consentBuilder);
        firebaseAnalytics.setConsent(consentBuilder.asMap());
    }
}
