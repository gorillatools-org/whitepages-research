package com.google.android.gms.measurement.internal;

final class zzol {
    final /* synthetic */ zzop zza;
    private zzok zzb;

    zzol(zzop zzop) {
        this.zza = zzop;
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j) {
        zzop zzop = this.zza;
        this.zzb = new zzok(this, zzop.zzu.zzaU().currentTimeMillis(), j);
        zzop.zzd.postDelayed(this.zzb, 2000);
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        zzop zzop = this.zza;
        zzop.zzg();
        zzok zzok = this.zzb;
        if (zzok != null) {
            zzop.zzd.removeCallbacks(zzok);
        }
        zzio zzio = zzop.zzu;
        zzio.zzm().zzn.zza(false);
        zzop.zzm(false);
        if (zzio.zzf().zzx((String) null, zzgi.zzaZ)) {
            zzio zzio2 = zzop.zzu;
            if (zzio2.zzq().zzap()) {
                zzio.zzaW().zzj().zza("Retrying trigger URI registration in foreground");
                zzio2.zzq().zzU();
            }
        }
    }
}
