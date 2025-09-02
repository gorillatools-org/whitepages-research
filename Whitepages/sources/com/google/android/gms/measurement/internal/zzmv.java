package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.atomic.AtomicReference;

final class zzmv implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ boolean zzc;
    final /* synthetic */ zzny zzd;

    zzmv(zzny zzny, AtomicReference atomicReference, zzr zzr, boolean z) {
        this.zza = atomicReference;
        this.zzb = zzr;
        this.zzc = z;
        this.zzd = zzny;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2 = this.zza;
        synchronized (atomicReference2) {
            try {
                zzny zzny = this.zzd;
                zzgl zzi = zzny.zzb;
                if (zzi == null) {
                    zzny.zzu.zzaW().zze().zza("Failed to get all user properties; not connected to service");
                    atomicReference2.notify();
                    return;
                }
                zzr zzr = this.zzb;
                Preconditions.checkNotNull(zzr);
                atomicReference2.set(zzi.zzh(zzr, this.zzc));
                zzny.zzag();
                atomicReference = this.zza;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzd.zzu.zzaW().zze().zzb("Failed to get all user properties; remote exception", e);
                    atomicReference = this.zza;
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
