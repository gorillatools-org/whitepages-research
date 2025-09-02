package com.google.android.gms.internal.measurement;

public final class zzqn implements zzqm {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.sdk.collection.enable_extend_user_property_size", true);
        zza = zza2.zzf("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zza2.zzd("measurement.id.sdk.collection.last_deep_link_referrer2", 0);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
