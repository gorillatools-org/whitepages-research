package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zziq implements Runnable {
    public final /* synthetic */ zzjp zza;
    public final /* synthetic */ zzr zzb;
    public final /* synthetic */ Bundle zzc;
    public final /* synthetic */ zzgo zzd;
    public final /* synthetic */ String zze;

    public /* synthetic */ zziq(zzjp zzjp, zzr zzr, Bundle bundle, zzgo zzgo, String str) {
        this.zza = zzjp;
        this.zzb = zzr;
        this.zzc = bundle;
        this.zzd = zzgo;
        this.zze = str;
    }

    public final void run() {
        zzjp.zzH(this.zza, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
