package com.google.firebase.crashlytics.ktx;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import kotlin.jvm.internal.Intrinsics;

public final class KeyValueBuilder {
    private final FirebaseCrashlytics crashlytics;

    public KeyValueBuilder(FirebaseCrashlytics firebaseCrashlytics) {
        Intrinsics.checkNotNullParameter(firebaseCrashlytics, "crashlytics");
        this.crashlytics = firebaseCrashlytics;
    }

    public final void key(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, z);
    }

    public final void key(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, d);
    }

    public final void key(String str, float f) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, f);
    }

    public final void key(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, i);
    }

    public final void key(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.crashlytics.setCustomKey(str, j);
    }

    public final void key(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.crashlytics.setCustomKey(str, str2);
    }
}
