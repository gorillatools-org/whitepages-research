package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

final class zzkt implements FutureCallback {
    final /* synthetic */ zzov zza;
    final /* synthetic */ zzlw zzb;

    zzkt(zzlw zzlw, zzov zzov) {
        this.zza = zzov;
        this.zzb = zzlw;
    }

    private final void zza() {
        zzio zzio = this.zzb.zzu;
        SparseArray zze = zzio.zzm().zze();
        zzov zzov = this.zza;
        zze.put(zzov.zzc, Long.valueOf(zzov.zzb));
        zzht zzm = zzio.zzm();
        int[] iArr = new int[zze.size()];
        long[] jArr = new long[zze.size()];
        for (int i = 0; i < zze.size(); i++) {
            iArr[i] = zze.keyAt(i);
            jArr[i] = ((Long) zze.valueAt(i)).longValue();
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("uriSources", iArr);
        bundle.putLongArray("uriTimestamps", jArr);
        zzm.zzi.zzb(bundle);
    }

    public final void onFailure(Throwable th) {
        zzlw zzlw = this.zzb;
        zzlw.zzg();
        zzlw.zzi = false;
        zzio zzio = zzlw.zzu;
        int zzaq = (zzio.zzf().zzx((String) null, zzgi.zzaZ) ? zzlw.zzaq(zzlw, th) : 2) - 1;
        if (zzaq == 0) {
            zzio.zzaW().zzk().zzc("registerTriggerAsync failed with retriable error. Will try later. App ID, throwable", zzhe.zzn(zzlw.zzu.zzh().zzm()), zzhe.zzn(th.toString()));
            zzlw.zzj = 1;
            zzlw.zzy().add(this.zza);
        } else if (zzaq != 1) {
            zzio.zzaW().zze().zzc("registerTriggerAsync failed. Dropping URI. App ID, Throwable", zzhe.zzn(zzlw.zzu.zzh().zzm()), th);
            zza();
            zzlw.zzj = 1;
            zzlw.zzU();
        } else {
            zzlw.zzy().add(this.zza);
            if (zzlw.zzj > ((Integer) zzgi.zzav.zza((Object) null)).intValue()) {
                zzlw.zzj = 1;
                zzio.zzaW().zzk().zzc("registerTriggerAsync failed. May try later. App ID, throwable", zzhe.zzn(zzlw.zzu.zzh().zzm()), zzhe.zzn(th.toString()));
                return;
            }
            zzio.zzaW().zzk().zzd("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzhe.zzn(zzlw.zzu.zzh().zzm()), zzhe.zzn(String.valueOf(zzlw.zzj)), zzhe.zzn(th.toString()));
            zzlw.zzF(zzlw, zzlw.zzj);
            int zzh = zzlw.zzj;
            zzlw.zzj = zzh + zzh;
        }
    }

    public final void onSuccess(Object obj) {
        zzlw zzlw = this.zzb;
        zzlw.zzg();
        zza();
        zzlw.zzi = false;
        zzlw.zzj = 1;
        zzlw.zzu.zzaW().zzd().zzb("Successfully registered trigger URI", this.zza.zza);
        zzlw.zzU();
    }
}
