package com.google.android.gms.measurement.internal;

final class zzja implements Runnable {
    final /* synthetic */ zzai zza;
    final /* synthetic */ zzjp zzb;

    zzja(zzjp zzjp, zzai zzai) {
        this.zza = zzai;
        this.zzb = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        zzai zzai = this.zza;
        if (zzai.zzc.zza() == null) {
            zzjp.zza.zzae(zzai);
        } else {
            zzjp.zza.zzan(zzai);
        }
    }
}
