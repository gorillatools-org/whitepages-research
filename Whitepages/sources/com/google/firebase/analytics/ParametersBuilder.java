package com.google.firebase.analytics;

import android.os.Bundle;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public final class ParametersBuilder {
    private final Bundle zza = new Bundle();

    public final Bundle getBundle() {
        return this.zza;
    }

    public final void param(String str, double d) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.zza.putDouble(str, d);
    }

    public final void param(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.zza.putLong(str, j);
    }

    public final void param(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(bundle, "value");
        this.zza.putBundle(str, bundle);
    }

    public final void param(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        this.zza.putString(str, str2);
    }

    public final void param(String str, Bundle[] bundleArr) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(bundleArr, "value");
        this.zza.putParcelableArray(str, (Parcelable[]) bundleArr);
    }
}
