package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzdj;

final class zzlv implements Application.ActivityLifecycleCallbacks, zzlt {
    final /* synthetic */ zzlw zza;

    zzlv(zzlw zzlw) {
        this.zza = zzlw;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zza(zzdj.zza(activity), bundle);
    }

    public final void onActivityDestroyed(Activity activity) {
        zzb(zzdj.zza(activity));
    }

    public final void onActivityPaused(Activity activity) {
        zzc(zzdj.zza(activity));
    }

    public final void onActivityResumed(Activity activity) {
        zzd(zzdj.zza(activity));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        zze(zzdj.zza(activity), bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044 A[Catch:{ RuntimeException -> 0x0027, all -> 0x0024 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007c A[Catch:{ RuntimeException -> 0x0027, all -> 0x0024 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007f A[Catch:{ RuntimeException -> 0x0027, all -> 0x0024 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.measurement.zzdj r9, android.os.Bundle r10) {
        /*
            r8 = this;
            com.google.android.gms.measurement.internal.zzlw r0 = r8.zza     // Catch:{ RuntimeException -> 0x0027 }
            com.google.android.gms.measurement.internal.zzio r1 = r0.zzu     // Catch:{ RuntimeException -> 0x0027 }
            com.google.android.gms.measurement.internal.zzhe r2 = r1.zzaW()     // Catch:{ RuntimeException -> 0x0027 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()     // Catch:{ RuntimeException -> 0x0027 }
            java.lang.String r3 = "onActivityCreated"
            r2.zza(r3)     // Catch:{ RuntimeException -> 0x0027 }
            android.content.Intent r2 = r9.zzc     // Catch:{ RuntimeException -> 0x0027 }
            if (r2 == 0) goto L_0x0090
            android.net.Uri r3 = r2.getData()     // Catch:{ RuntimeException -> 0x0027 }
            if (r3 == 0) goto L_0x002a
            boolean r4 = r3.isHierarchical()     // Catch:{ RuntimeException -> 0x0027 }
            if (r4 != 0) goto L_0x0022
            goto L_0x002a
        L_0x0022:
            r4 = r3
            goto L_0x0042
        L_0x0024:
            r0 = move-exception
            goto L_0x00b0
        L_0x0027:
            r0 = move-exception
            goto L_0x009a
        L_0x002a:
            android.os.Bundle r3 = r2.getExtras()     // Catch:{ RuntimeException -> 0x0027 }
            r4 = 0
            if (r3 == 0) goto L_0x0042
            java.lang.String r5 = "com.android.vending.referral_url"
            java.lang.String r3 = r3.getString(r5)     // Catch:{ RuntimeException -> 0x0027 }
            boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ RuntimeException -> 0x0027 }
            if (r5 != 0) goto L_0x0042
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ RuntimeException -> 0x0027 }
            goto L_0x0022
        L_0x0042:
            if (r4 == 0) goto L_0x0090
            boolean r3 = r4.isHierarchical()     // Catch:{ RuntimeException -> 0x0027 }
            if (r3 != 0) goto L_0x004b
            goto L_0x0090
        L_0x004b:
            r1.zzw()     // Catch:{ RuntimeException -> 0x0027 }
            java.lang.String r0 = "android.intent.extra.REFERRER_NAME"
            java.lang.String r0 = r2.getStringExtra(r0)     // Catch:{ RuntimeException -> 0x0027 }
            java.lang.String r2 = "android-app://com.google.android.googlequicksearchbox/https/www.google.com"
            boolean r2 = r2.equals(r0)     // Catch:{ RuntimeException -> 0x0027 }
            if (r2 != 0) goto L_0x0071
            java.lang.String r2 = "https://www.google.com"
            boolean r2 = r2.equals(r0)     // Catch:{ RuntimeException -> 0x0027 }
            if (r2 != 0) goto L_0x0071
            java.lang.String r2 = "android-app://com.google.appcrawler"
            boolean r0 = r2.equals(r0)     // Catch:{ RuntimeException -> 0x0027 }
            if (r0 == 0) goto L_0x006d
            goto L_0x0071
        L_0x006d:
            java.lang.String r0 = "auto"
        L_0x006f:
            r5 = r0
            goto L_0x0074
        L_0x0071:
            java.lang.String r0 = "gs"
            goto L_0x006f
        L_0x0074:
            java.lang.String r0 = "referrer"
            java.lang.String r6 = r4.getQueryParameter(r0)     // Catch:{ RuntimeException -> 0x0027 }
            if (r10 != 0) goto L_0x007f
            r0 = 1
        L_0x007d:
            r3 = r0
            goto L_0x0081
        L_0x007f:
            r0 = 0
            goto L_0x007d
        L_0x0081:
            com.google.android.gms.measurement.internal.zzil r0 = r1.zzaX()     // Catch:{ RuntimeException -> 0x0027 }
            com.google.android.gms.measurement.internal.zzlu r7 = new com.google.android.gms.measurement.internal.zzlu     // Catch:{ RuntimeException -> 0x0027 }
            r1 = r7
            r2 = r8
            r1.<init>(r2, r3, r4, r5, r6)     // Catch:{ RuntimeException -> 0x0027 }
            r0.zzq(r7)     // Catch:{ RuntimeException -> 0x0027 }
            goto L_0x00ab
        L_0x0090:
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
        L_0x0092:
            com.google.android.gms.measurement.internal.zzmo r0 = r0.zzt()
            r0.zzs(r9, r10)
            return
        L_0x009a:
            com.google.android.gms.measurement.internal.zzlw r1 = r8.zza     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhe r1 = r1.zzaW()     // Catch:{ all -> 0x0024 }
            com.google.android.gms.measurement.internal.zzhc r1 = r1.zze()     // Catch:{ all -> 0x0024 }
            java.lang.String r2 = "Throwable caught in onActivityCreated"
            r1.zzb(r2, r0)     // Catch:{ all -> 0x0024 }
        L_0x00ab:
            com.google.android.gms.measurement.internal.zzlw r0 = r8.zza
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            goto L_0x0092
        L_0x00b0:
            com.google.android.gms.measurement.internal.zzlw r1 = r8.zza
            com.google.android.gms.measurement.internal.zzio r1 = r1.zzu
            com.google.android.gms.measurement.internal.zzmo r1 = r1.zzt()
            r1.zzs(r9, r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzlv.zza(com.google.android.gms.internal.measurement.zzdj, android.os.Bundle):void");
    }

    public final void zzb(zzdj zzdj) {
        this.zza.zzu.zzt().zzt(zzdj);
    }

    public final void zzc(zzdj zzdj) {
        zzio zzio = this.zza.zzu;
        zzio.zzt().zzu(zzdj);
        zzop zzv = zzio.zzv();
        zzio zzio2 = zzv.zzu;
        zzio2.zzaX().zzq(new zzoi(zzv, zzio2.zzaU().elapsedRealtime()));
    }

    public final void zzd(zzdj zzdj) {
        zzio zzio = this.zza.zzu;
        zzop zzv = zzio.zzv();
        zzio zzio2 = zzv.zzu;
        zzio2.zzaX().zzq(new zzoh(zzv, zzio2.zzaU().elapsedRealtime()));
        zzio.zzt().zzv(zzdj);
    }

    public final void zze(zzdj zzdj, Bundle bundle) {
        this.zza.zzu.zzt().zzw(zzdj, bundle);
    }
}
