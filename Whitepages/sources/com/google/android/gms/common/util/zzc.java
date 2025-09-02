package com.google.android.gms.common.util;

import android.os.StrictMode;

public final class zzc {
    public static StrictMode.VmPolicy zza() {
        StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
        if (PlatformVersion.isAtLeastS()) {
            StrictMode.setVmPolicy(zzb.zza(new StrictMode.VmPolicy.Builder(vmPolicy)).build());
        }
        return vmPolicy;
    }
}
