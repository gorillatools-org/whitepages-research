package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzip implements Runnable {
    public final /* synthetic */ zzjp zza;
    public final /* synthetic */ zzr zzb;
    public final /* synthetic */ zzag zzc;

    public /* synthetic */ zzip(zzjp zzjp, zzr zzr, zzag zzag) {
        this.zza = zzjp;
        this.zzb = zzr;
        this.zzc = zzag;
    }

    public final void run() {
        zzjp.zzF(this.zza, this.zzb, this.zzc);
    }
}
