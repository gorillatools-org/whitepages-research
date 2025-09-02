package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzon {
    protected long zza;
    protected long zzb;
    final /* synthetic */ zzop zzc;
    private final zzaz zzd;

    public zzon(zzop zzop) {
        this.zzc = zzop;
        this.zzd = new zzom(this, zzop.zzu);
        long elapsedRealtime = zzop.zzu.zzaU().elapsedRealtime();
        this.zza = elapsedRealtime;
        this.zzb = elapsedRealtime;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzd.zzb();
        zzio zzio = this.zzc.zzu;
        long elapsedRealtime = zzio.zzf().zzx((String) null, zzgi.zzbb) ? zzio.zzaU().elapsedRealtime() : 0;
        this.zza = elapsedRealtime;
        this.zzb = elapsedRealtime;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j) {
        this.zzd.zzb();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(long j) {
        this.zzc.zzg();
        this.zzd.zzb();
        this.zza = j;
        this.zzb = j;
    }

    public final boolean zzd(boolean z, boolean z2, long j) {
        zzop zzop = this.zzc;
        zzop.zzg();
        zzop.zza();
        if (zzop.zzu.zzJ()) {
            zzio zzio = zzop.zzu;
            zzio.zzm().zzk.zzb(zzio.zzaU().currentTimeMillis());
        }
        long j2 = j - this.zza;
        if (z || j2 >= 1000) {
            if (!z2) {
                j2 = j - this.zzb;
                this.zzb = j;
            }
            zzio zzio2 = zzop.zzu;
            zzio2.zzaW().zzj().zzb("Recording user engagement, ms", Long.valueOf(j2));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j2);
            zzio zzio3 = zzop.zzu;
            zzqf.zzN(zzio3.zzt().zzj(!zzio2.zzf().zzz()), bundle, true);
            if (!z2) {
                zzio3.zzq().zzR("auto", "_e", bundle);
            }
            this.zza = j;
            zzaz zzaz = this.zzd;
            zzaz.zzb();
            zzaz.zzd(((Long) zzgi.zzap.zza((Object) null)).longValue());
            return true;
        }
        zzop.zzu.zzaW().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
        return false;
    }
}
