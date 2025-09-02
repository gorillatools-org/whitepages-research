package com.google.android.gms.measurement.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.internal.measurement.zzcr;

public final class zzop extends zzg {
    protected final zzoo zza = new zzoo(this);
    protected final zzon zzb = new zzon(this);
    protected final zzol zzc = new zzol(this);
    /* access modifiers changed from: private */
    public Handler zzd;
    private boolean zze = true;

    zzop(zzio zzio) {
        super(zzio);
    }

    static /* bridge */ /* synthetic */ void zzj(zzop zzop, long j) {
        zzop.zzg();
        zzop.zzq();
        zzio zzio = zzop.zzu;
        zzio.zzaW().zzj().zzb("Activity paused, time", Long.valueOf(j));
        zzop.zzc.zza(j);
        if (zzio.zzf().zzz()) {
            zzop.zzb.zzb(j);
        }
    }

    static /* bridge */ /* synthetic */ void zzl(zzop zzop, long j) {
        zzop.zzg();
        zzop.zzq();
        zzio zzio = zzop.zzu;
        zzio.zzaW().zzj().zzb("Activity resumed, time", Long.valueOf(j));
        if (zzio.zzf().zzx((String) null, zzgi.zzba)) {
            if (zzio.zzf().zzz() || zzop.zze) {
                zzop.zzb.zzc(j);
            }
        } else if (zzio.zzf().zzz() || zzio.zzm().zzn.zzb()) {
            zzop.zzb.zzc(j);
        }
        zzop.zzc.zzb();
        zzoo zzoo = zzop.zza;
        zzop zzop2 = zzoo.zza;
        zzop2.zzg();
        if (zzop2.zzu.zzJ()) {
            zzoo.zzb(zzop2.zzu.zzaU().currentTimeMillis(), false);
        }
    }

    /* access modifiers changed from: private */
    public final void zzq() {
        zzg();
        if (this.zzd == null) {
            this.zzd = new zzcr(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final void zzm(boolean z) {
        zzg();
        this.zze = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzp() {
        zzg();
        return this.zze;
    }
}
