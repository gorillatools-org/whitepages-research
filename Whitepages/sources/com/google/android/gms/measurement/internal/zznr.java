package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

final class zznr implements Runnable {
    final /* synthetic */ ComponentName zza;
    final /* synthetic */ zznx zzb;

    zznr(zznx zznx, ComponentName componentName) {
        this.zza = componentName;
        this.zzb = zznx;
    }

    public final void run() {
        zzny.zzx(this.zzb.zza, this.zza);
    }
}
