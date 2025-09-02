package com.google.android.gms.measurement.internal;

import android.content.Intent;

final class zzpn extends zzaz {
    final /* synthetic */ zzpv zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzpn(zzpv zzpv, zzjs zzjs) {
        super(zzjs);
        this.zza = zzpv;
    }

    public final void zzc() {
        zzpv zzpv = this.zza;
        zzpv.zzaX().zzg();
        String str = (String) zzpv.zzr.pollFirst();
        if (str != null) {
            zzpv.zzJ = zzpv.zzaU().elapsedRealtime();
            zzpv.zzaW().zzj().zzb("Sending trigger URI notification to app", str);
            Intent intent = new Intent();
            intent.setAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
            intent.setPackage(str);
            zzpv.zzaK(zzpv.zzaT(), intent);
        }
        zzpv.zzaJ();
    }
}
