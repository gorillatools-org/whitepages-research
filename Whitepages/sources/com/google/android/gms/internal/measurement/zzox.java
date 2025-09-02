package com.google.android.gms.internal.measurement;

public final class zzox implements zzow {
    public static final zzki zza;
    public static final zzki zzb;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.admob_plus_removal.client.dev", false);
        zzb = zza2.zzf("measurement.admob_plus_removal.service", false);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    public final boolean zzb() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }
}
