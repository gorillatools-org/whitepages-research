package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

final class zzhh implements Runnable {
    private final zzhg zza;
    private final int zzb;
    private final Throwable zzc;
    private final byte[] zzd;
    private final String zze;
    private final Map zzf;

    /* synthetic */ zzhh(String str, zzhg zzhg, int i, Throwable th, byte[] bArr, Map map, zzhj zzhj) {
        Preconditions.checkNotNull(zzhg);
        this.zza = zzhg;
        this.zzb = i;
        this.zzc = th;
        this.zzd = bArr;
        this.zze = str;
        this.zzf = map;
    }

    public final void run() {
        this.zza.zza(this.zze, this.zzb, this.zzc, this.zzd, this.zzf);
    }
}
