package com.google.android.gms.measurement.internal;

public final /* synthetic */ class zzoj implements Runnable {
    public final /* synthetic */ zzok zza;

    public /* synthetic */ zzoj(zzok zzok) {
        this.zza = zzok;
    }

    public final void run() {
        long j;
        zzok zzok = this.zza;
        zzop zzop = zzok.zzc.zza;
        long j2 = zzok.zza;
        long j3 = zzok.zzb;
        zzop.zzg();
        zzio zzio = zzop.zzu;
        zzio.zzaW().zzd().zza("Application going to the background");
        zzio.zzm().zzn.zza(true);
        zzop.zzm(true);
        if (!zzio.zzf().zzz()) {
            zzon zzon = zzop.zzb;
            zzon.zzd(false, false, j3);
            zzon.zzb(j3);
        }
        zzio.zzaW().zzi().zzb("Application backgrounded at: timestamp_millis", Long.valueOf(j2));
        zzio zzio2 = zzop.zzu;
        zzlw zzq = zzio2.zzq();
        zzq.zzg();
        zzio zzio3 = zzq.zzu;
        zzq.zza();
        zzny zzu = zzio3.zzu();
        zzu.zzg();
        zzu.zza();
        if (!zzu.zzad() || zzu.zzu.zzw().zzm() >= 242600) {
            zzio3.zzu().zzz();
        }
        if (zzio.zzf().zzx((String) null, zzgi.zzaS)) {
            if (zzio.zzw().zzak(zzio.zzaT().getPackageName(), zzio.zzf().zzs())) {
                j = 1000;
            } else {
                j = zzio.zzf().zzk(zzio.zzaT().getPackageName(), zzgi.zzD);
            }
            zzio.zzaW().zzj().zzb("[sgtm] Scheduling batch upload with minimum latency in millis", Long.valueOf(j));
            zzio2.zzs().zzj(j);
        }
    }
}
