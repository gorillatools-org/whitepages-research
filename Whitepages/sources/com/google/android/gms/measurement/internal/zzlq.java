package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;

final class zzlq implements Runnable {
    final /* synthetic */ zzba zza;
    final /* synthetic */ zzlw zzb;

    zzlq(zzlw zzlw, zzba zzba) {
        this.zza = zzba;
        this.zzb = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzb;
        zzio zzio = zzlw.zzu;
        zzht zzm = zzio.zzm();
        zzio zzio2 = zzm.zzu;
        zzm.zzg();
        zzba zzf = zzm.zzf();
        zzba zzba = this.zza;
        if (zzjx.zzs(zzba.zza(), zzf.zza())) {
            SharedPreferences.Editor edit = zzm.zzb().edit();
            edit.putString("dma_consent_settings", zzba.zzj());
            edit.apply();
            zzio.zzaW().zzj().zzb("Setting DMA consent(FE)", zzba);
            zzio zzio3 = zzlw.zzu;
            if (zzio3.zzu().zzac()) {
                zzio3.zzu().zzU();
            } else {
                zzio3.zzu().zzR(false);
            }
        } else {
            zzio.zzaW().zzi().zzb("Lower precedence consent source ignored, proposed source", Integer.valueOf(zzba.zza()));
        }
    }
}
