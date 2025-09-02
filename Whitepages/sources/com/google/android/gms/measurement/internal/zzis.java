package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzis implements Runnable {
    public final /* synthetic */ zzjp zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzpc zzc;
    public final /* synthetic */ zzgr zzd;

    public /* synthetic */ zzis(zzjp zzjp, String str, zzpc zzpc, zzgr zzgr) {
        this.zza = zzjp;
        this.zzb = str;
        this.zzc = zzpc;
        this.zzd = zzgr;
    }

    public final void run() {
        zzjp.zzd(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
