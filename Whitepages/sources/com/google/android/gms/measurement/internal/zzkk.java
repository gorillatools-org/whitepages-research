package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final /* synthetic */ class zzkk implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ Bundle zzb;

    public /* synthetic */ zzkk(zzlw zzlw, Bundle bundle) {
        this.zza = zzlw;
        this.zzb = bundle;
    }

    public final void run() {
        zzlw.zzz(this.zza, this.zzb);
    }
}
