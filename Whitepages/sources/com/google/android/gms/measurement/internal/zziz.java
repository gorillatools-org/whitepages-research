package com.google.android.gms.measurement.internal;

final class zziz implements Runnable {
    final /* synthetic */ zzai zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjp zzc;

    zziz(zzjp zzjp, zzai zzai, zzr zzr) {
        this.zza = zzai;
        this.zzb = zzr;
        this.zzc = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzc;
        zzjp.zza.zzL();
        zzai zzai = this.zza;
        if (zzai.zzc.zza() == null) {
            zzjp.zza.zzaf(zzai, this.zzb);
            return;
        }
        zzjp.zza.zzao(zzai, this.zzb);
    }
}
