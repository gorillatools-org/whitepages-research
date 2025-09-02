package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

public final /* synthetic */ class zzkm implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ Bundle zzb;
    public final /* synthetic */ long zzc;

    public /* synthetic */ zzkm(zzlw zzlw, Bundle bundle, long j) {
        this.zza = zzlw;
        this.zzb = bundle;
        this.zzc = j;
    }

    public final void run() {
        zzlw zzlw = this.zza;
        if (TextUtils.isEmpty(zzlw.zzu.zzh().zzo())) {
            zzlw.zzaf(this.zzb, 0, this.zzc);
            return;
        }
        zzlw.zzu.zzaW().zzl().zza("Using developer consent only; google app id found");
    }
}
