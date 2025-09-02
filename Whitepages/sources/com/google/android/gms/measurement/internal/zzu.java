package com.google.android.gms.measurement.internal;

import java.util.Objects;

public final /* synthetic */ class zzu implements Runnable {
    public final /* synthetic */ zzio zza;

    public /* synthetic */ zzu(zzio zzio) {
        this.zza = zzio;
    }

    public final void run() {
        zzio zzio = this.zza;
        if (!zzio.zzw().zzan()) {
            zzio.zzaW().zzk().zza("registerTrigger called but app not eligible");
            return;
        }
        zzio.zzq().zzI();
        zzlw zzq = zzio.zzq();
        Objects.requireNonNull(zzq);
        new Thread(new zzt(zzq)).start();
    }
}
