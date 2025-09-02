package com.google.android.gms.measurement.internal;

import java.util.Objects;

final class zzkr extends zzaz {
    final /* synthetic */ zzlw zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzkr(zzlw zzlw, zzjs zzjs) {
        super(zzjs);
        this.zza = zzlw;
    }

    public final void zzc() {
        zzlw zzq = this.zza.zzu.zzq();
        Objects.requireNonNull(zzq);
        new Thread(new zzkq(zzq)).start();
    }
}
