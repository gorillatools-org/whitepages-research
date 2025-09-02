package com.google.android.gms.internal.measurement;

public final class zzri implements zzrh {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.client.sessions.background_sessions_enabled", true);
        zza = zza2.zzf("measurement.client.sessions.enable_fix_background_engagement", false);
        zza2.zzf("measurement.client.sessions.immediate_start_enabled_foreground", true);
        zza2.zzf("measurement.client.sessions.enable_pause_engagement_in_background", true);
        zza2.zzf("measurement.client.sessions.session_id_enabled", true);
        zza2.zzd("measurement.id.client.sessions.enable_fix_background_engagement", 0);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
