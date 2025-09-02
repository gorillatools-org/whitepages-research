package com.google.android.gms.internal.measurement;

final class zzbe implements zzbf {
    private final zzg zza;
    private final String zzb;

    public zzbe(zzg zzg, String str) {
        this.zza = zzg;
        this.zzb = str;
    }

    public final zzg zza(zzap zzap) {
        zzg zza2 = this.zza.zza();
        zza2.zze(this.zzb, zzap);
        return zza2;
    }
}
