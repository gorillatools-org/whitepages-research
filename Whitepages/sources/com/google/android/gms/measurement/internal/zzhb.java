package com.google.android.gms.measurement.internal;

import android.util.Log;

final class zzhb implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ Object zzd;
    final /* synthetic */ Object zze;
    final /* synthetic */ zzhe zzf;

    zzhb(zzhe zzhe, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
        this.zzf = zzhe;
    }

    public final void run() {
        zzhe zzhe = this.zzf;
        zzht zzm = zzhe.zzu.zzm();
        if (zzm.zzy()) {
            if (zzhe.zza == 0) {
                zzio zzio = zzhe.zzu;
                if (zzio.zzf().zzD()) {
                    zzio.zzaV();
                    zzhe.zza = 'C';
                } else {
                    zzio.zzaV();
                    zzhe.zza = 'c';
                }
            }
            if (zzhe.zzb < 0) {
                zzhe.zzu.zzf().zzj();
                zzhe.zzb = 119002;
            }
            char charAt = "01VDIWEA?".charAt(this.zza);
            char zza2 = zzhe.zza;
            long zzb2 = zzhe.zzb;
            String str = this.zzb;
            String str2 = "2" + charAt + zza2 + zzb2 + ":" + zzhe.zzo(true, str, this.zzc, this.zzd, this.zze);
            if (str2.length() > 1024) {
                str2 = str.substring(0, 1024);
            }
            zzhq zzhq = zzm.zzb;
            if (zzhq != null) {
                zzhq.zzb(str2, 1);
                return;
            }
            return;
        }
        Log.println(6, zzhe.zzr(), "Persisted config not initialized. Not logging error/warn");
    }
}
