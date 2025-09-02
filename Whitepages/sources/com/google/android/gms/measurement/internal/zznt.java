package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zznt implements Runnable {
    final /* synthetic */ zznx zza;

    zznt(zznx zznx) {
        this.zza = zznx;
    }

    public final void run() {
        zzny zzny = this.zza.zza;
        zzio zzio = zzny.zzu;
        Context zzaT = zzio.zzaT();
        zzio.zzaV();
        zzny.zzx(zzny, new ComponentName(zzaT, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
