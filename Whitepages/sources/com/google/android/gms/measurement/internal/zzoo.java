package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.salesforce.marketingcloud.notifications.NotificationMessage;

final class zzoo {
    final /* synthetic */ zzop zza;

    zzoo(zzop zzop) {
        this.zza = zzop;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        zzop zzop = this.zza;
        zzop.zzg();
        zzio zzio = zzop.zzu;
        if (zzio.zzm().zzp(zzio.zzaU().currentTimeMillis())) {
            zzio.zzm().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                zzio.zzaW().zzj().zza("Detected application was in foreground");
                zzc(zzio.zzaU().currentTimeMillis(), false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j, boolean z) {
        zzop zzop = this.zza;
        zzop.zzg();
        zzop.zzq();
        zzio zzio = zzop.zzu;
        if (zzio.zzm().zzp(j)) {
            zzio.zzm().zzg.zza(true);
            zzop.zzu.zzh().zzq();
        }
        zzio.zzm().zzk.zzb(j);
        if (zzio.zzm().zzg.zzb()) {
            zzc(j, z);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(long j, boolean z) {
        zzop zzop = this.zza;
        zzop.zzg();
        if (zzop.zzu.zzJ()) {
            zzio zzio = zzop.zzu;
            zzio.zzm().zzk.zzb(j);
            zzio.zzaW().zzj().zzb("Session started, time", Long.valueOf(zzio.zzaU().elapsedRealtime()));
            long j2 = j / 1000;
            zzio zzio2 = zzop.zzu;
            zzio2.zzq().zzan("auto", NotificationMessage.NOTIF_KEY_SID, Long.valueOf(j2), j);
            zzio.zzm().zzl.zzb(j2);
            zzio.zzm().zzg.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong(NotificationMessage.NOTIF_KEY_SID, j2);
            zzio2.zzq().zzS("auto", "_s", j, bundle);
            String zza2 = zzio.zzm().zzq.zza();
            if (!TextUtils.isEmpty(zza2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", zza2);
                zzio2.zzq().zzS("auto", "_ssr", j, bundle2);
            }
        }
    }
}
