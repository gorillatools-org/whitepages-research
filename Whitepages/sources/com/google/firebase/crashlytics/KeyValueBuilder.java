package com.google.firebase.crashlytics;

import com.google.firebase.crashlytics.CustomKeysAndValues;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

public final class KeyValueBuilder {
    private final CustomKeysAndValues.Builder builder;
    private final FirebaseCrashlytics crashlytics;

    private KeyValueBuilder(FirebaseCrashlytics firebaseCrashlytics, CustomKeysAndValues.Builder builder2) {
        this.crashlytics = firebaseCrashlytics;
        this.builder = builder2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public KeyValueBuilder(FirebaseCrashlytics firebaseCrashlytics) {
        this(firebaseCrashlytics, new CustomKeysAndValues.Builder());
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "crashlytics");
    }

    public KeyValueBuilder() {
        this((FirebaseCrashlytics) null, new CustomKeysAndValues.Builder());
    }

    public final CustomKeysAndValues build$com_google_firebase_firebase_crashlytics() {
        CustomKeysAndValues build = this.builder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    public final void key(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, z);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putBoolean(str, z), "builder.putBoolean(key, value)");
    }

    public final void key(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, d);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putDouble(str, d), "builder.putDouble(key, value)");
    }

    public final void key(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, f);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putFloat(str, f), "builder.putFloat(key, value)");
    }

    public final void key(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, i);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putInt(str, i), "builder.putInt(key, value)");
    }

    public final void key(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, j);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putLong(str, j), "builder.putLong(key, value)");
    }

    public final void key(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        FirebaseCrashlytics firebaseCrashlytics = this.crashlytics;
        if (firebaseCrashlytics != null) {
            firebaseCrashlytics.setCustomKey(str, str2);
            Unit unit = Unit.INSTANCE;
            return;
        }
        Intrinsics.checkNotNullExpressionValue(this.builder.putString(str, str2), "builder.putString(key, value)");
    }
}
