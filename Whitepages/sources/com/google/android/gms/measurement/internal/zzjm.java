package com.google.android.gms.measurement.internal;

final class zzjm implements Runnable {
    final /* synthetic */ zzqb zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjp zzc;

    zzjm(zzjp zzjp, zzqb zzqb, zzr zzr) {
        this.zza = zzqb;
        this.zzb = zzr;
        this.zzc = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzc;
        zzjp.zza.zzL();
        zzqb zzqb = this.zza;
        if (zzqb.zza() == null) {
            zzjp.zza.zzag(zzqb.zzb, this.zzb);
            return;
        }
        zzjp.zza.zzas(zzqb, this.zzb);
    }
}
