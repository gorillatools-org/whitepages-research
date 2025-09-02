package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzlj implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ zzlw zzb;

    zzlj(zzlw zzlw, zzcy zzcy) {
        this.zza = zzcy;
        this.zzb = zzlw;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x006d A[SYNTHETIC, Splitter:B:12:0x006d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zzlw r0 = r8.zzb
            com.google.android.gms.measurement.internal.zzio r1 = r0.zzu
            com.google.android.gms.measurement.internal.zzop r1 = r1.zzv()
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzht r2 = r1.zzm()
            com.google.android.gms.measurement.internal.zzjx r2 = r2.zzh()
            com.google.android.gms.measurement.internal.zzjw r3 = com.google.android.gms.measurement.internal.zzjw.ANALYTICS_STORAGE
            boolean r2 = r2.zzr(r3)
            r3 = 0
            if (r2 != 0) goto L_0x002a
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzl()
            java.lang.String r2 = "Analytics storage consent denied; will not get session id"
            r1.zza(r2)
        L_0x0028:
            r1 = r3
            goto L_0x005b
        L_0x002a:
            com.google.android.gms.measurement.internal.zzht r2 = r1.zzm()
            com.google.android.gms.common.util.Clock r4 = r1.zzaU()
            long r4 = r4.currentTimeMillis()
            boolean r2 = r2.zzp(r4)
            if (r2 != 0) goto L_0x0028
            com.google.android.gms.measurement.internal.zzht r2 = r1.zzm()
            com.google.android.gms.measurement.internal.zzhp r2 = r2.zzl
            long r4 = r2.zza()
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 != 0) goto L_0x004d
            goto L_0x0028
        L_0x004d:
            com.google.android.gms.measurement.internal.zzht r1 = r1.zzm()
            com.google.android.gms.measurement.internal.zzhp r1 = r1.zzl
            long r1 = r1.zza()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
        L_0x005b:
            if (r1 == 0) goto L_0x006d
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.internal.measurement.zzcy r2 = r8.zza
            com.google.android.gms.measurement.internal.zzqf r0 = r0.zzw()
            long r3 = r1.longValue()
            r0.zzY(r2, r3)
            return
        L_0x006d:
            com.google.android.gms.internal.measurement.zzcy r0 = r8.zza     // Catch:{ RemoteException -> 0x0073 }
            r0.zze(r3)     // Catch:{ RemoteException -> 0x0073 }
            return
        L_0x0073:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzlw r1 = r8.zzb
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()
            java.lang.String r2 = "getSessionId failed with exception"
            r1.zzb(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlj.run():void");
    }
}
