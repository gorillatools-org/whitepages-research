package com.google.android.gms.measurement.internal;

import java.util.Map;

public final /* synthetic */ class zzlz implements Runnable {
    public final /* synthetic */ zzma zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ Exception zzc;
    public final /* synthetic */ byte[] zzd;
    public final /* synthetic */ Map zze;

    public /* synthetic */ zzlz(zzma zzma, int i, Exception exc, byte[] bArr, Map map) {
        this.zza = zzma;
        this.zzb = i;
        this.zzc = exc;
        this.zzd = bArr;
        this.zze = map;
    }

    public final void run() {
        this.zza.zzd.zza(this.zza.zze, this.zzb, this.zzc, this.zzd, this.zze);
    }
}
