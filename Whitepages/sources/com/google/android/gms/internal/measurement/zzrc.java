package com.google.android.gms.internal.measurement;

public final class zzrc implements zzrb {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.backfill_session_ids.service", false);
        zza2.zzd("measurement.id.backfill_session_ids.service", 0);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
