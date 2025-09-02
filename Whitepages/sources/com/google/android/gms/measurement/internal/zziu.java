package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zziu implements Runnable {
    public final /* synthetic */ zzjp zza;
    public final /* synthetic */ Bundle zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ zzr zzd;

    public /* synthetic */ zziu(zzjp zzjp, Bundle bundle, String str, zzr zzr) {
        this.zza = zzjp;
        this.zzb = bundle;
        this.zzc = str;
        this.zzd = zzr;
    }

    public final void run() {
        zzjp.zzI(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
