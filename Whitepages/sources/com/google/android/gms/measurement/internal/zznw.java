package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

final class zznw implements Runnable {
    final /* synthetic */ ConnectionResult zza;
    final /* synthetic */ zznx zzb;

    zznw(zznx zznx, ConnectionResult connectionResult) {
        this.zza = connectionResult;
        this.zzb = zznx;
    }

    public final void run() {
        zzny zzny = this.zzb.zza;
        zzny.zzb = null;
        if (!zzny.zzu.zzf().zzx((String) null, zzgi.zzbo) || this.zza.getErrorCode() != 7777) {
            zzny.zzaf();
            return;
        }
        if (zzny.zze == null) {
            zzny.zze = Executors.newScheduledThreadPool(1);
        }
        zzny.zze.schedule(new zznv(this), ((Long) zzgi.zzY.zza((Object) null)).longValue(), TimeUnit.MILLISECONDS);
    }
}
