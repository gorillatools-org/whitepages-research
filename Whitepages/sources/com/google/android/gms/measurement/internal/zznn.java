package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zznn implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzr zzd;
    final /* synthetic */ zzny zze;

    zznn(zzny zzny, AtomicReference atomicReference, String str, String str2, String str3, zzr zzr) {
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzr;
        this.zze = zzny;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2 = this.zza;
        synchronized (atomicReference2) {
            try {
                zzny zzny = this.zze;
                zzgl zzi = zzny.zzb;
                if (zzi == null) {
                    zzny.zzu.zzaW().zze().zzd("(legacy) Failed to get conditional properties; not connected to service", (Object) null, this.zzb, this.zzc);
                    atomicReference2.set(Collections.emptyList());
                    atomicReference2.notify();
                    return;
                }
                if (TextUtils.isEmpty((CharSequence) null)) {
                    zzr zzr = this.zzd;
                    Preconditions.checkNotNull(zzr);
                    atomicReference2.set(zzi.zzi(this.zzb, this.zzc, zzr));
                } else {
                    atomicReference2.set(zzi.zzj((String) null, this.zzb, this.zzc));
                }
                zzny.zzag();
                atomicReference = this.zza;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zze.zzu.zzaW().zze().zzd("(legacy) Failed to get conditional properties; remote exception", (Object) null, this.zzb, e);
                    this.zza.set(Collections.emptyList());
                    atomicReference = this.zza;
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
