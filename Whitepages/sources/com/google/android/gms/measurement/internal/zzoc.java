package com.google.android.gms.measurement.internal;

import android.content.Intent;

public final /* synthetic */ class zzoc implements Runnable {
    public final /* synthetic */ zzog zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ zzhe zzc;
    public final /* synthetic */ Intent zzd;

    public /* synthetic */ zzoc(zzog zzog, int i, zzhe zzhe, Intent intent) {
        this.zza = zzog;
        this.zzb = i;
        this.zzc = zzhe;
        this.zzd = intent;
    }

    public final void run() {
        zzog.zze(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
