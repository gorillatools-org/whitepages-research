package com.google.android.gms.internal.measurement;

public final class zzqb implements zzqa {
    public static final zzki zza;

    static {
        zzkf zza2 = new zzkf(zzjx.zza("com.google.android.gms.measurement")).zzb().zza();
        zza2.zzd("measurement.id.upload_controller_wait_initialization", 0);
        zza = zza2.zzf("measurement.upload_controller.wait_initialization", false);
    }

    public final boolean zza() {
        return ((Boolean) zza.zzb()).booleanValue();
    }
}
