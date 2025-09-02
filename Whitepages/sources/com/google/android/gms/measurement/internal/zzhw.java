package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;

public final class zzhw {
    final zzio zza;

    zzhw(zzpv zzpv) {
        this.zza = zzpv.zzt();
    }

    /* access modifiers changed from: package-private */
    public final boolean zza() {
        try {
            zzio zzio = this.zza;
            PackageManagerWrapper packageManager = Wrappers.packageManager(zzio.zzaT());
            if (packageManager == null) {
                zzio.zzaW().zzj().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
                return false;
            } else if (packageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            this.zza.zzaW().zzj().zzb("Failed to retrieve Play Store version for Install Referrer", e);
            return false;
        }
    }
}
