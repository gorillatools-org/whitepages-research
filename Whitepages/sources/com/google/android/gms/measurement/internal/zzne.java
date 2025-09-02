package com.google.android.gms.measurement.internal;

final class zzne extends zzaz {
    final /* synthetic */ zzny zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzne(zzny zzny, zzjs zzjs) {
        super(zzjs);
        this.zza = zzny;
    }

    public final void zzc() {
        zzny zzny = this.zza;
        zzny.zzg();
        if (zzny.zzaa()) {
            zzny.zzu.zzaW().zzj().zza("Inactivity, disconnecting from the service");
            zzny.zzC();
        }
    }
}
