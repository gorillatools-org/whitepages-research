package com.google.android.gms.internal.measurement;

public final class zzrf implements zzre {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.collection.enable_session_stitching_token.client.dev", true);
        zza = zza2.zzf("measurement.session_stitching_token_enabled", false);
        zza2.zzf("measurement.link_sst_to_sid", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
