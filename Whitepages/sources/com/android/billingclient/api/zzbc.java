package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzhl;
import com.google.android.gms.internal.play_billing.zzr;

final class zzbc implements ServiceConnection {
    final /* synthetic */ BillingClientImpl zza;
    private final Object zzb = new Object();
    private boolean zzc = false;
    private BillingClientStateListener zzd;

    /* synthetic */ zzbc(BillingClientImpl billingClientImpl, BillingClientStateListener billingClientStateListener, zzbb zzbb) {
        this.zza = billingClientImpl;
        this.zzd = billingClientStateListener;
    }

    private final void zzd(BillingResult billingResult) {
        synchronized (this.zzb) {
            try {
                BillingClientStateListener billingClientStateListener = this.zzd;
                if (billingClientStateListener != null) {
                    billingClientStateListener.onBillingSetupFinished(billingResult);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzj("BillingClient", "Billing service connected.");
        this.zza.zzg = zzr.zzu(iBinder);
        zzaz zzaz = new zzaz(this);
        zzba zzba = new zzba(this);
        BillingClientImpl billingClientImpl = this.zza;
        if (billingClientImpl.zzao(zzaz, 30000, zzba, billingClientImpl.zzaj()) == null) {
            BillingClientImpl billingClientImpl2 = this.zza;
            BillingResult zzi = billingClientImpl2.zzal();
            billingClientImpl2.zzap(zzcb.zza(25, 6, zzi));
            zzd(zzi);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzk("BillingClient", "Billing service disconnected.");
        this.zza.zzf.zze(zzhl.zzz());
        this.zza.zzg = null;
        this.zza.zza = 0;
        synchronized (this.zzb) {
            try {
                BillingClientStateListener billingClientStateListener = this.zzd;
                if (billingClientStateListener != null) {
                    billingClientStateListener.onBillingServiceDisconnected();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0202  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0244  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0252  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x025d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object zza() {
        /*
            r14 = this;
            java.lang.Object r0 = r14.zzb
            monitor-enter(r0)
            boolean r1 = r14.zzc     // Catch:{ all -> 0x000b }
            r2 = 0
            if (r1 == 0) goto L_0x000e
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            goto L_0x026b
        L_0x000b:
            r1 = move-exception
            goto L_0x026c
        L_0x000e:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0020
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "accountName"
            r0.putString(r1, r2)
            goto L_0x0021
        L_0x0020:
            r0 = r2
        L_0x0021:
            r1 = 6
            r3 = 3
            r4 = 0
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza     // Catch:{ Exception -> 0x01f0 }
            android.content.Context r5 = r5.zze     // Catch:{ Exception -> 0x01f0 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ Exception -> 0x01f0 }
            r6 = 22
            r8 = r3
            r7 = r6
        L_0x0032:
            if (r7 < r3) goto L_0x006f
            if (r0 != 0) goto L_0x0043
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzs r9 = r9.zzg     // Catch:{ Exception -> 0x0068 }
            java.lang.String r10 = "subs"
            int r8 = r9.zzy(r7, r5, r10)     // Catch:{ Exception -> 0x0068 }
            goto L_0x004f
        L_0x0043:
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzs r9 = r9.zzg     // Catch:{ Exception -> 0x0068 }
            java.lang.String r10 = "subs"
            int r8 = r9.zzc(r7, r5, r10, r0)     // Catch:{ Exception -> 0x0068 }
        L_0x004f:
            if (r8 != 0) goto L_0x006c
            java.lang.String r9 = "BillingClient"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068 }
            r10.<init>()     // Catch:{ Exception -> 0x0068 }
            java.lang.String r11 = "highestLevelSupportedForSubs: "
            r10.append(r11)     // Catch:{ Exception -> 0x0068 }
            r10.append(r7)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzb.zzj(r9, r10)     // Catch:{ Exception -> 0x0068 }
            goto L_0x0070
        L_0x0068:
            r0 = move-exception
            r3 = r8
            goto L_0x01f1
        L_0x006c:
            int r7 = r7 + -1
            goto L_0x0032
        L_0x006f:
            r7 = r4
        L_0x0070:
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x0068 }
            r10 = 5
            r11 = 1
            if (r7 < r10) goto L_0x0078
            r10 = r11
            goto L_0x0079
        L_0x0078:
            r10 = r4
        L_0x0079:
            r9.zzj = r10     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x0068 }
            if (r7 < r3) goto L_0x0082
            r10 = r11
            goto L_0x0083
        L_0x0082:
            r10 = r4
        L_0x0083:
            r9.zzi = r10     // Catch:{ Exception -> 0x0068 }
            r9 = 9
            if (r7 >= r3) goto L_0x0093
            java.lang.String r7 = "BillingClient"
            java.lang.String r10 = "In-app billing API does not support subscription on this device."
            com.google.android.gms.internal.play_billing.zzb.zzj(r7, r10)     // Catch:{ Exception -> 0x0068 }
            r7 = r9
            goto L_0x0094
        L_0x0093:
            r7 = r11
        L_0x0094:
            r10 = r6
        L_0x0095:
            if (r10 < r3) goto L_0x00d9
            if (r0 != 0) goto L_0x00a6
            com.android.billingclient.api.BillingClientImpl r12 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzs r12 = r12.zzg     // Catch:{ Exception -> 0x0068 }
            java.lang.String r13 = "inapp"
            int r8 = r12.zzy(r10, r5, r13)     // Catch:{ Exception -> 0x0068 }
            goto L_0x00b2
        L_0x00a6:
            com.android.billingclient.api.BillingClientImpl r12 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzs r12 = r12.zzg     // Catch:{ Exception -> 0x0068 }
            java.lang.String r13 = "inapp"
            int r8 = r12.zzc(r10, r5, r13, r0)     // Catch:{ Exception -> 0x0068 }
        L_0x00b2:
            if (r8 != 0) goto L_0x00d6
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            r0.zzk = r10     // Catch:{ Exception -> 0x0068 }
            java.lang.String r0 = "BillingClient"
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r5.zzk     // Catch:{ Exception -> 0x0068 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0068 }
            r10.<init>()     // Catch:{ Exception -> 0x0068 }
            java.lang.String r12 = "mHighestLevelSupportedForInApp: "
            r10.append(r12)     // Catch:{ Exception -> 0x0068 }
            r10.append(r5)     // Catch:{ Exception -> 0x0068 }
            java.lang.String r5 = r10.toString()     // Catch:{ Exception -> 0x0068 }
            com.google.android.gms.internal.play_billing.zzb.zzj(r0, r5)     // Catch:{ Exception -> 0x0068 }
            goto L_0x00d9
        L_0x00d6:
            int r10 = r10 + -1
            goto L_0x0095
        L_0x00d9:
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            if (r5 < r6) goto L_0x00e3
            r5 = r11
            goto L_0x00e4
        L_0x00e3:
            r5 = r4
        L_0x00e4:
            r0.zzy = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 21
            if (r5 < r6) goto L_0x00f3
            r5 = r11
            goto L_0x00f4
        L_0x00f3:
            r5 = r4
        L_0x00f4:
            r0.zzx = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 20
            if (r5 < r6) goto L_0x0103
            r5 = r11
            goto L_0x0104
        L_0x0103:
            r5 = r4
        L_0x0104:
            r0.zzw = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 19
            if (r5 < r6) goto L_0x0113
            r5 = r11
            goto L_0x0114
        L_0x0113:
            r5 = r4
        L_0x0114:
            r0.zzv = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 18
            if (r5 < r6) goto L_0x0123
            r5 = r11
            goto L_0x0124
        L_0x0123:
            r5 = r4
        L_0x0124:
            r0.zzu = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 17
            if (r5 < r6) goto L_0x0133
            r5 = r11
            goto L_0x0134
        L_0x0133:
            r5 = r4
        L_0x0134:
            r0.zzt = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 16
            if (r5 < r6) goto L_0x0143
            r5 = r11
            goto L_0x0144
        L_0x0143:
            r5 = r4
        L_0x0144:
            r0.zzs = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 15
            if (r5 < r6) goto L_0x0153
            r5 = r11
            goto L_0x0154
        L_0x0153:
            r5 = r4
        L_0x0154:
            r0.zzr = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 14
            if (r5 < r6) goto L_0x0163
            r5 = r11
            goto L_0x0164
        L_0x0163:
            r5 = r4
        L_0x0164:
            r0.zzq = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 12
            if (r5 < r6) goto L_0x0173
            r5 = r11
            goto L_0x0174
        L_0x0173:
            r5 = r4
        L_0x0174:
            r0.zzp = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 10
            if (r5 < r6) goto L_0x0183
            r5 = r11
            goto L_0x0184
        L_0x0183:
            r5 = r4
        L_0x0184:
            r0.zzo = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            if (r5 < r9) goto L_0x0191
            r5 = r11
            goto L_0x0192
        L_0x0191:
            r5 = r4
        L_0x0192:
            r0.zzn = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            r6 = 8
            if (r5 < r6) goto L_0x01a1
            r5 = r11
            goto L_0x01a2
        L_0x01a1:
            r5 = r4
        L_0x01a2:
            r0.zzm = r5     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            if (r5 < r1) goto L_0x01ae
            goto L_0x01af
        L_0x01ae:
            r11 = r4
        L_0x01af:
            r0.zzl = r11     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            int r0 = r0.zzk     // Catch:{ Exception -> 0x0068 }
            if (r0 >= r3) goto L_0x01c3
            java.lang.String r0 = "BillingClient"
            java.lang.String r3 = "In-app billing API version 3 is not supported on this device."
            com.google.android.gms.internal.play_billing.zzb.zzk(r0, r3)     // Catch:{ Exception -> 0x0068 }
            r7 = 36
        L_0x01c3:
            if (r8 != 0) goto L_0x01e5
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            r3 = 2
            r0.zza = r3     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.zzo r0 = r0.zzd     // Catch:{ Exception -> 0x0068 }
            if (r0 == 0) goto L_0x01e2
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.zzo r0 = r0.zzd     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r3 = r14.zza     // Catch:{ Exception -> 0x0068 }
            boolean r3 = r3.zzx     // Catch:{ Exception -> 0x0068 }
            r0.zzg(r3)     // Catch:{ Exception -> 0x0068 }
        L_0x01e2:
            r0 = r2
            goto L_0x0250
        L_0x01e5:
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            r0.zza = r4     // Catch:{ Exception -> 0x0068 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x0068 }
            r0.zzg = r2     // Catch:{ Exception -> 0x0068 }
            goto L_0x01e2
        L_0x01f0:
            r0 = move-exception
        L_0x01f1:
            java.lang.String r5 = "BillingClient"
            java.lang.String r6 = "Exception while checking if billing is supported; try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r5, r6, r0)
            boolean r5 = r0 instanceof android.os.DeadObjectException
            r6 = 42
            if (r5 == 0) goto L_0x0202
            r5 = 101(0x65, float:1.42E-43)
        L_0x0200:
            r7 = r5
            goto L_0x0211
        L_0x0202:
            boolean r5 = r0 instanceof android.os.RemoteException
            if (r5 == 0) goto L_0x0209
            r5 = 100
            goto L_0x0200
        L_0x0209:
            boolean r5 = r0 instanceof java.lang.SecurityException
            if (r5 == 0) goto L_0x0210
            r5 = 102(0x66, float:1.43E-43)
            goto L_0x0200
        L_0x0210:
            r7 = r6
        L_0x0211:
            if (r7 != r6) goto L_0x0244
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = com.google.android.gms.internal.play_billing.zzab.zzb(r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = ": "
            r6.append(r5)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            int r5 = r0.length()
            r6 = 70
            if (r5 <= r6) goto L_0x0245
            java.lang.String r0 = r0.substring(r4, r6)
            goto L_0x0245
        L_0x0244:
            r0 = r2
        L_0x0245:
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza
            r5.zza = r4
            com.android.billingclient.api.BillingClientImpl r4 = r14.zza
            r4.zzg = r2
            r8 = r3
        L_0x0250:
            if (r8 != 0) goto L_0x025d
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza
            r0.zzaq(com.android.billingclient.api.zzcb.zzc(6))
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzl
            r14.zzd(r0)
            goto L_0x026b
        L_0x025d:
            com.android.billingclient.api.BillingClientImpl r3 = r14.zza
            com.android.billingclient.api.BillingResult r4 = com.android.billingclient.api.zzce.zza
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zzb(r7, r1, r4, r0)
            r3.zzap(r0)
            r14.zzd(r4)
        L_0x026b:
            return r2
        L_0x026c:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.zzbc.zza():java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb() {
        this.zza.zza = 0;
        this.zza.zzg = null;
        BillingResult billingResult = zzce.zzn;
        this.zza.zzap(zzcb.zza(24, 6, billingResult));
        zzd(billingResult);
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        synchronized (this.zzb) {
            this.zzd = null;
            this.zzc = true;
        }
    }
}
