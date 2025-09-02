package com.google.android.gms.measurement.internal;

import android.net.Uri;

final class zzlu implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ Uri zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzlv zze;

    zzlu(zzlv zzlv, boolean z, Uri uri, String str, String str2) {
        this.zza = z;
        this.zzb = uri;
        this.zzc = str;
        this.zzd = str2;
        this.zze = zzlv;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0095 A[SYNTHETIC, Splitter:B:33:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00d3 A[Catch:{ RuntimeException -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d4 A[Catch:{ RuntimeException -> 0x0075 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r17 = this;
            r1 = r17
            com.google.android.gms.measurement.internal.zzlv r2 = r1.zze
            com.google.android.gms.measurement.internal.zzlw r0 = r2.zza
            r0.zzg()
            java.lang.String r3 = r1.zzd
            android.net.Uri r4 = r1.zzb
            com.google.android.gms.measurement.internal.zzio r5 = r0.zzu     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzqf r6 = r5.zzw()     // Catch:{ RuntimeException -> 0x0075 }
            boolean r7 = android.text.TextUtils.isEmpty(r3)     // Catch:{ RuntimeException -> 0x0075 }
            java.lang.String r8 = "_cis"
            java.lang.String r9 = "Activity created with data 'referrer' without required params"
            java.lang.String r10 = "utm_medium"
            java.lang.String r11 = "utm_source"
            java.lang.String r12 = "utm_campaign"
            java.lang.String r14 = "gclid"
            if (r7 == 0) goto L_0x0027
        L_0x0025:
            r6 = 0
            goto L_0x008d
        L_0x0027:
            boolean r7 = r3.contains(r14)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            java.lang.String r7 = "gbraid"
            boolean r7 = r3.contains(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            boolean r7 = r3.contains(r12)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            boolean r7 = r3.contains(r11)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            boolean r7 = r3.contains(r10)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            java.lang.String r7 = "utm_id"
            boolean r7 = r3.contains(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            java.lang.String r7 = "dclid"
            boolean r7 = r3.contains(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            java.lang.String r7 = "srsltid"
            boolean r7 = r3.contains(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            java.lang.String r7 = "sfmc_id"
            boolean r7 = r3.contains(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r7 != 0) goto L_0x0078
            com.google.android.gms.measurement.internal.zzio r6 = r6.zzu     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zzd()     // Catch:{ RuntimeException -> 0x0075 }
            r6.zza(r9)     // Catch:{ RuntimeException -> 0x0075 }
            goto L_0x0025
        L_0x0075:
            r0 = move-exception
            goto L_0x014f
        L_0x0078:
            java.lang.String r7 = "https://google.com/search?"
            java.lang.String r7 = r7.concat(r3)     // Catch:{ RuntimeException -> 0x0075 }
            android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ RuntimeException -> 0x0075 }
            android.os.Bundle r6 = r6.zzu(r7)     // Catch:{ RuntimeException -> 0x0075 }
            if (r6 == 0) goto L_0x008d
            java.lang.String r7 = "referrer"
            r6.putString(r8, r7)     // Catch:{ RuntimeException -> 0x0075 }
        L_0x008d:
            java.lang.String r7 = r1.zzc
            boolean r15 = r1.zza
            java.lang.String r13 = "_cmp"
            if (r15 == 0) goto L_0x00cd
            com.google.android.gms.measurement.internal.zzqf r15 = r5.zzw()     // Catch:{ RuntimeException -> 0x0075 }
            android.os.Bundle r4 = r15.zzu(r4)     // Catch:{ RuntimeException -> 0x0075 }
            if (r4 == 0) goto L_0x00cd
            java.lang.String r15 = "intent"
            r4.putString(r8, r15)     // Catch:{ RuntimeException -> 0x0075 }
            boolean r8 = r4.containsKey(r14)     // Catch:{ RuntimeException -> 0x0075 }
            if (r8 != 0) goto L_0x00c5
            if (r6 == 0) goto L_0x00c5
            boolean r8 = r6.containsKey(r14)     // Catch:{ RuntimeException -> 0x0075 }
            if (r8 == 0) goto L_0x00c5
            java.lang.String r8 = "_cer"
            java.lang.String r15 = "gclid=%s"
            java.lang.String r16 = r6.getString(r14)     // Catch:{ RuntimeException -> 0x0075 }
            java.lang.Object[] r1 = new java.lang.Object[]{r16}     // Catch:{ RuntimeException -> 0x0075 }
            java.lang.String r1 = java.lang.String.format(r15, r1)     // Catch:{ RuntimeException -> 0x0075 }
            r4.putString(r8, r1)     // Catch:{ RuntimeException -> 0x0075 }
        L_0x00c5:
            r0.zzR(r7, r13, r4)     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzx r1 = r0.zzb     // Catch:{ RuntimeException -> 0x0075 }
            r1.zza(r7, r4)     // Catch:{ RuntimeException -> 0x0075 }
        L_0x00cd:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 == 0) goto L_0x00d4
            goto L_0x0142
        L_0x00d4:
            com.google.android.gms.measurement.internal.zzhe r1 = r5.zzaW()     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzd()     // Catch:{ RuntimeException -> 0x0075 }
            java.lang.String r4 = "Activity created with referrer"
            r1.zzb(r4, r3)     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzam r1 = r5.zzf()     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzaF     // Catch:{ RuntimeException -> 0x0075 }
            r8 = 0
            boolean r1 = r1.zzx(r8, r4)     // Catch:{ RuntimeException -> 0x0075 }
            r4 = 1
            java.lang.String r8 = "_ldl"
            java.lang.String r15 = "auto"
            if (r1 == 0) goto L_0x0111
            if (r6 == 0) goto L_0x00ff
            r0.zzR(r7, r13, r6)     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzx r1 = r0.zzb     // Catch:{ RuntimeException -> 0x0075 }
            r1.zza(r7, r6)     // Catch:{ RuntimeException -> 0x0075 }
        L_0x00fd:
            r1 = 0
            goto L_0x010d
        L_0x00ff:
            com.google.android.gms.measurement.internal.zzhe r1 = r5.zzaW()     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zzd()     // Catch:{ RuntimeException -> 0x0075 }
            java.lang.String r5 = "Referrer does not contain valid parameters"
            r1.zzb(r5, r3)     // Catch:{ RuntimeException -> 0x0075 }
            goto L_0x00fd
        L_0x010d:
            r0.zzal(r15, r8, r1, r4)     // Catch:{ RuntimeException -> 0x0075 }
            return
        L_0x0111:
            boolean r1 = r3.contains(r14)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 == 0) goto L_0x0143
            boolean r1 = r3.contains(r12)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 != 0) goto L_0x0139
            boolean r1 = r3.contains(r11)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 != 0) goto L_0x0139
            boolean r1 = r3.contains(r10)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 != 0) goto L_0x0139
            java.lang.String r1 = "utm_term"
            boolean r1 = r3.contains(r1)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 != 0) goto L_0x0139
            java.lang.String r1 = "utm_content"
            boolean r1 = r3.contains(r1)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 == 0) goto L_0x0143
        L_0x0139:
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ RuntimeException -> 0x0075 }
            if (r1 != 0) goto L_0x0142
            r0.zzal(r15, r8, r3, r4)     // Catch:{ RuntimeException -> 0x0075 }
        L_0x0142:
            return
        L_0x0143:
            com.google.android.gms.measurement.internal.zzhe r0 = r5.zzaW()     // Catch:{ RuntimeException -> 0x0075 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzd()     // Catch:{ RuntimeException -> 0x0075 }
            r0.zza(r9)     // Catch:{ RuntimeException -> 0x0075 }
            return
        L_0x014f:
            com.google.android.gms.measurement.internal.zzlw r1 = r2.zza
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()
            java.lang.String r2 = "Throwable caught in handleReferrerForOnActivityCreated"
            r1.zzb(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlu.run():void");
    }
}
