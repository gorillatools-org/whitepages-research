package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;

public final class zzw extends BroadcastReceiver {
    private final zzio zza;

    public zzw(zzio zzio) {
        this.zza = zzio;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onReceive(android.content.Context r3, android.content.Intent r4) {
        /*
            r2 = this;
            if (r4 != 0) goto L_0x0012
            com.google.android.gms.measurement.internal.zzio r3 = r2.zza
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()
            java.lang.String r4 = "App receiver called with null intent"
            r3.zza(r4)
            return
        L_0x0012:
            java.lang.String r3 = r4.getAction()
            if (r3 != 0) goto L_0x0028
            com.google.android.gms.measurement.internal.zzio r3 = r2.zza
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()
            java.lang.String r4 = "App receiver called with null action"
            r3.zza(r4)
            return
        L_0x0028:
            int r4 = r3.hashCode()
            r0 = -1928239649(0xffffffff8d1165df, float:-4.4804198E-31)
            r1 = 1
            if (r4 == r0) goto L_0x0042
            r0 = 1279883384(0x4c497878, float:5.2814304E7)
            if (r4 == r0) goto L_0x0038
            goto L_0x004c
        L_0x0038:
            java.lang.String r4 = "com.google.android.gms.measurement.BATCHES_AVAILABLE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004c
            r3 = r1
            goto L_0x004d
        L_0x0042:
            java.lang.String r4 = "com.google.android.gms.measurement.TRIGGERS_AVAILABLE"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x004c
            r3 = 0
            goto L_0x004d
        L_0x004c:
            r3 = -1
        L_0x004d:
            r4 = 0
            if (r3 == 0) goto L_0x008a
            if (r3 == r1) goto L_0x0062
            com.google.android.gms.measurement.internal.zzio r3 = r2.zza
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zzk()
            java.lang.String r4 = "App receiver called with unknown action"
            r3.zza(r4)
            return
        L_0x0062:
            com.google.android.gms.measurement.internal.zzio r3 = r2.zza
            com.google.android.gms.measurement.internal.zzam r0 = r3.zzf()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzaR
            boolean r4 = r0.zzx(r4, r1)
            if (r4 == 0) goto L_0x009b
            com.google.android.gms.measurement.internal.zzhe r4 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            java.lang.String r0 = "[sgtm] App Receiver notified batches are available"
            r4.zza(r0)
            com.google.android.gms.measurement.internal.zzil r3 = r3.zzaX()
            com.google.android.gms.measurement.internal.zzv r4 = new com.google.android.gms.measurement.internal.zzv
            r4.<init>(r2)
            r3.zzq(r4)
            return
        L_0x008a:
            com.google.android.gms.measurement.internal.zzio r3 = r2.zza
            com.google.android.gms.internal.measurement.zzqr.zzb()
            com.google.android.gms.measurement.internal.zzam r0 = r3.zzf()
            com.google.android.gms.measurement.internal.zzgg r1 = com.google.android.gms.measurement.internal.zzgi.zzaW
            boolean r4 = r0.zzx(r4, r1)
            if (r4 != 0) goto L_0x009c
        L_0x009b:
            return
        L_0x009c:
            com.google.android.gms.measurement.internal.zzhe r4 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r4 = r4.zzj()
            java.lang.String r0 = "App receiver notified triggers are available"
            r4.zza(r0)
            com.google.android.gms.measurement.internal.zzil r4 = r3.zzaX()
            com.google.android.gms.measurement.internal.zzu r0 = new com.google.android.gms.measurement.internal.zzu
            r0.<init>(r3)
            r4.zzq(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzw.onReceive(android.content.Context, android.content.Intent):void");
    }
}
