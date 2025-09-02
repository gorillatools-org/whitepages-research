package com.google.firebase.crashlytics;

import com.google.firebase.Firebase;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class FirebaseCrashlyticsKt {
    public static final FirebaseCrashlytics getCrashlytics(Firebase firebase2) {
        Intrinsics.checkNotNullParameter(firebase2, "<this>");
        FirebaseCrashlytics instance = FirebaseCrashlytics.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final void setCustomKeys(FirebaseCrashlytics firebaseCrashlytics, Function1 function1) {
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "<this>");
        Intrinsics.checkNotNullParameter(function1, "init");
        KeyValueBuilder keyValueBuilder = new KeyValueBuilder();
        function1.invoke(keyValueBuilder);
        firebaseCrashlytics.setCustomKeys(keyValueBuilder.build$com_google_firebase_firebase_crashlytics());
    }

    public static final void recordException(FirebaseCrashlytics firebaseCrashlytics, Throwable th, Function1 function1) {
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "<this>");
        Intrinsics.checkNotNullParameter(th, "throwable");
        Intrinsics.checkNotNullParameter(function1, "init");
        KeyValueBuilder keyValueBuilder = new KeyValueBuilder();
        function1.invoke(keyValueBuilder);
        firebaseCrashlytics.recordException(th, keyValueBuilder.build$com_google_firebase_firebase_crashlytics());
    }
}
