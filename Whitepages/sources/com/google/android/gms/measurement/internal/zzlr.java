package com.google.android.gms.measurement.internal;

final class zzlr implements Runnable {
    final /* synthetic */ zzjx zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ zzlw zzd;

    zzlr(zzlw zzlw, zzjx zzjx, long j, boolean z) {
        this.zza = zzjx;
        this.zzb = j;
        this.zzc = z;
        this.zzd = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzd;
        zzjx zzjx = this.zza;
        zzlw.zzaj(zzjx);
        zzlw.zzD(zzlw, zzjx, this.zzb, true, this.zzc);
    }
}
