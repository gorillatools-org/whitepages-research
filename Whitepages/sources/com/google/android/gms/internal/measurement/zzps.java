package com.google.android.gms.internal.measurement;

public final class zzps implements zzpr {
    public static final zzki zza;
    public static final zzki zzb;
    public static final zzki zzc;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzf("measurement.service.audience.fix_skip_audience_with_failed_filters", true);
        zza = zza2.zzf("measurement.audience.refresh_event_count_filters_timestamp", false);
        zzb = zza2.zzf("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false);
        zzc = zza2.zzf("measurement.audience.use_bundle_timestamp_for_event_count_filters", false);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    public final boolean zzc() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }

    public final boolean zzd() {
        return ((Boolean) zzc.zzb()).booleanValue();
    }
}
