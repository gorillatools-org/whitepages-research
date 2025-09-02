package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

final class zzpp implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Bundle zzc;
    final /* synthetic */ zzpq zzd;

    zzpp(zzpq zzpq, String str, String str2, Bundle bundle) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = bundle;
        this.zzd = zzpq;
    }

    public final void run() {
        zzpv zzpv = this.zzd.zza;
        zzqf zzB = zzpv.zzB();
        long currentTimeMillis = zzpv.zzaU().currentTimeMillis();
        String str = this.zza;
        zzpv.zzT((zzbh) Preconditions.checkNotNull(zzB.zzC(str, this.zzb, this.zzc, "auto", currentTimeMillis, false, true)), str);
    }
}
