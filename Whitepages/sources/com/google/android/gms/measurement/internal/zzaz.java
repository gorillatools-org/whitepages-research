package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcr;

abstract class zzaz {
    private static volatile Handler zza;
    private final zzjs zzb;
    private final Runnable zzc;
    /* access modifiers changed from: private */
    public volatile long zzd;

    zzaz(zzjs zzjs) {
        Preconditions.checkNotNull(zzjs);
        this.zzb = zzjs;
        this.zzc = new zzay(this, zzjs);
    }

    private final Handler zzf() {
        Handler handler;
        if (zza != null) {
            return zza;
        }
        synchronized (zzaz.class) {
            try {
                if (zza == null) {
                    zza = new zzcr(this.zzb.zzaT().getMainLooper());
                }
                handler = zza;
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        this.zzd = 0;
        zzf().removeCallbacks(this.zzc);
    }

    public abstract void zzc();

    public final void zzd(long j) {
        zzb();
        if (j >= 0) {
            zzjs zzjs = this.zzb;
            this.zzd = zzjs.zzaU().currentTimeMillis();
            if (!zzf().postDelayed(this.zzc, j)) {
                zzjs.zzaW().zze().zzb("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zze() {
        return this.zzd != 0;
    }
}
