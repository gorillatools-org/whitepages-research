package com.google.android.gms.common.util;

import android.os.StrictMode;

final class zzb {
    static StrictMode.VmPolicy.Builder zza(StrictMode.VmPolicy.Builder builder) {
        return builder.permitUnsafeIntentLaunch();
    }
}
