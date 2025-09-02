package com.google.firebase.crashlytics.ktx;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.ktx.Firebase;
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
        function1.invoke(new KeyValueBuilder(firebaseCrashlytics));
    }
}
