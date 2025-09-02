package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.util.SparseArray;
import java.util.List;

public final /* synthetic */ class zzkj implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ List zzb;

    public /* synthetic */ zzkj(zzlw zzlw, List list) {
        this.zza = zzlw;
        this.zzb = list;
    }

    public final void run() {
        zzlw zzlw = this.zza;
        zzlw.zzg();
        if (Build.VERSION.SDK_INT >= 30) {
            List<zzov> list = this.zzb;
            SparseArray zze = zzlw.zzu.zzm().zze();
            for (zzov zzov : list) {
                int i = zzov.zzc;
                if (!zze.contains(i) || ((Long) zze.get(i)).longValue() < zzov.zzb) {
                    zzlw.zzy().add(zzov);
                }
            }
            zzlw.zzU();
        }
    }
}
