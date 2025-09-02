package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzkg implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzkg(zzlw zzlw, String str) {
        this.zza = zzlw;
        this.zzb = str;
    }

    public final void run() {
        zzio zzio = this.zza.zzu;
        if (zzio.zzh().zzr(this.zzb)) {
            zzio.zzh().zzq();
        }
    }
}
