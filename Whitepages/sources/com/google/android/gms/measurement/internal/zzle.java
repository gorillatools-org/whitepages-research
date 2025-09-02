package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.concurrent.atomic.AtomicReference;

final class zzle implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzlw zzb;

    zzle(zzlw zzlw, long j) {
        this.zza = j;
        this.zzb = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzb;
        zzlw.zzg();
        zzlw.zza();
        zzio zzio = zzlw.zzu;
        zzio.zzaW().zzd().zza("Resetting analytics data (FE)");
        zzio zzio2 = zzlw.zzu;
        zzop zzv = zzio2.zzv();
        zzv.zzg();
        zzv.zzb.zza();
        zzio2.zzh().zzq();
        boolean z = !zzlw.zzu.zzJ();
        zzht zzm = zzio.zzm();
        zzm.zzc.zzb(this.zza);
        zzio zzio3 = zzm.zzu;
        if (!TextUtils.isEmpty(zzio3.zzm().zzq.zza())) {
            zzm.zzq.zzb((String) null);
        }
        zzm.zzk.zzb(0);
        zzm.zzl.zzb(0);
        if (!zzio3.zzf().zzA()) {
            zzm.zzn(z);
        }
        zzm.zzr.zzb((String) null);
        zzm.zzs.zzb(0);
        zzm.zzt.zzb((Bundle) null);
        zzio2.zzu().zzO();
        zzio2.zzv().zza.zza();
        zzlw.zzc = z;
        zzio2.zzu().zzE(new AtomicReference());
    }
}
