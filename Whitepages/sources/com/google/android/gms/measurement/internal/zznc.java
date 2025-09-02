package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zznc implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzny zzb;

    zznc(zzny zzny, zzr zzr, boolean z) {
        this.zza = zzr;
        this.zzb = zzny;
    }

    public final void run() {
        zzny zzny = this.zzb;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzr zzr = this.zza;
            Preconditions.checkNotNull(zzr);
            zzio zzio = zzny.zzu;
            zzam zzf = zzio.zzf();
            zzgg zzgg = zzgi.zzbl;
            if (zzf.zzx((String) null, zzgg)) {
                zzny.zzP(zzi, (AbstractSafeParcelable) null, zzr);
            }
            zzi.zzn(zzr);
            zzny.zzu.zzi().zzm();
            zzio.zzf().zzx((String) null, zzgg);
            zzny.zzP(zzi, (AbstractSafeParcelable) null, zzr);
            zzny.zzag();
        } catch (RemoteException e) {
            this.zzb.zzu.zzaW().zze().zzb("Failed to send app launch to the service", e);
        }
    }
}
