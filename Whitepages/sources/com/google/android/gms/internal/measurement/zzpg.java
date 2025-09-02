package com.google.android.gms.internal.measurement;

public final class zzpg implements zzpf {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.client.3p_consent_state_v1", true);
        zza2.zzf("measurement.service.consent_state_v1_W36", true);
        zza = zza2.zzd("measurement.service.storage_consent_support_version", 203600);
    }

    public final long zza() {
        return ((Long) zza.zzb()).longValue();
    }
}
