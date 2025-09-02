package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zzna implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzny zzc;

    zzna(zzny zzny, AtomicReference atomicReference, zzr zzr) {
        this.zza = atomicReference;
        this.zzb = zzr;
        this.zzc = zzny;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2 = this.zza;
        synchronized (atomicReference2) {
            try {
                zzny zzny = this.zzc;
                zzio zzio = zzny.zzu;
                if (!zzio.zzm().zzh().zzr(zzjw.ANALYTICS_STORAGE)) {
                    zzio.zzaW().zzl().zza("Analytics storage consent denied; will not get app instance id");
                    zzny.zzu.zzq().zzac((String) null);
                    zzio.zzm().zze.zzb((String) null);
                    atomicReference2.set((Object) null);
                    atomicReference2.notify();
                    return;
                }
                zzgl zzi = zzny.zzb;
                if (zzi == null) {
                    zzio.zzaW().zze().zza("Failed to get app instance id");
                    atomicReference2.notify();
                    return;
                }
                zzr zzr = this.zzb;
                Preconditions.checkNotNull(zzr);
                atomicReference2.set(zzi.zzf(zzr));
                String str = (String) atomicReference2.get();
                if (str != null) {
                    zzny.zzu.zzq().zzac(str);
                    zzio.zzm().zze.zzb(str);
                }
                zzny.zzag();
                atomicReference = this.zza;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzc.zzu.zzaW().zze().zzb("Failed to get app instance id", e);
                    atomicReference = this.zza;
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
