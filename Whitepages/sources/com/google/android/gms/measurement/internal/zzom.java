package com.google.android.gms.measurement.internal;

final class zzom extends zzaz {
    final /* synthetic */ zzon zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzom(zzon zzon, zzjs zzjs) {
        super(zzjs);
        this.zza = zzon;
    }

    public final void zzc() {
        zzon zzon = this.zza;
        zzop zzop = zzon.zzc;
        zzop.zzg();
        zzio zzio = zzop.zzu;
        zzon.zzd(false, false, zzio.zzaU().elapsedRealtime());
        zzop.zzu.zzd().zzf(zzio.zzaU().elapsedRealtime());
    }
}
