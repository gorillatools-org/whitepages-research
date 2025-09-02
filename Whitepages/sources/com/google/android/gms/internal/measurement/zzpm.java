package com.google.android.gms.internal.measurement;

public final class zzpm implements zzpl {
    public static final zzki zza;
    public static final zzki zzb;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zzf("measurement.set_default_event_parameters_propagate_clear.client.dev", false);
        zzb = zza2.zzf("measurement.set_default_event_parameters_propagate_clear.service", false);
        zza2.zzd("measurement.id.set_default_event_parameters_propagate_clear.experiment_id", 0);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }

    public final boolean zzb() {
        return ((Boolean) zzb.zzb()).booleanValue();
    }
}
