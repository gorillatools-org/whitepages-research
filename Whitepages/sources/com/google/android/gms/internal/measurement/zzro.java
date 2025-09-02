package com.google.android.gms.internal.measurement;

public final class zzro implements zzrn {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.tcf.consent_fix", false);
        zza2.zzf("measurement.tcf.client", true);
        zza2.zzd("measurement.id.tcf", 0);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
