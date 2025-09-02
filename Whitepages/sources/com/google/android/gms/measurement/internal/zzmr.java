package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzmr implements Runnable {
    public final /* synthetic */ zzny zza;
    public final /* synthetic */ zzr zzb;
    public final /* synthetic */ zzag zzc;

    public /* synthetic */ zzmr(zzny zzny, zzr zzr, zzag zzag) {
        this.zza = zzny;
        this.zzb = zzr;
        this.zzc = zzag;
    }

    public final void run() {
        zzny.zzs(this.zza, this.zzb, this.zzc);
    }
}
