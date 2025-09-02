package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.common.zzh;
import java.util.HashMap;
import java.util.concurrent.Executor;

final class zzs extends GmsClientSupervisor {
    /* access modifiers changed from: private */
    public final HashMap zzb = new HashMap();
    /* access modifiers changed from: private */
    public final Context zzc;
    /* access modifiers changed from: private */
    public volatile Handler zzd;
    private final zzq zze;
    /* access modifiers changed from: private */
    public final ConnectionTracker zzf;
    private final long zzg;
    /* access modifiers changed from: private */
    public final long zzh;
    private volatile Executor zzi;

    zzs(Context context, Looper looper, Executor executor) {
        zzq zzq = new zzq(this, (zzr) null);
        this.zze = zzq;
        this.zzc = context.getApplicationContext();
        this.zzd = new zzh(looper, zzq);
        this.zzf = ConnectionTracker.getInstance();
        this.zzg = 5000;
        this.zzh = 300000;
        this.zzi = executor;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006c, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.common.ConnectionResult zza(com.google.android.gms.common.internal.zzo r5, android.content.ServiceConnection r6, java.lang.String r7, java.util.concurrent.Executor r8) {
        /*
            r4 = this;
            java.lang.String r0 = "ServiceConnection must not be null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6, r0)
            java.util.HashMap r0 = r4.zzb
            monitor-enter(r0)
            java.util.HashMap r1 = r4.zzb     // Catch:{ all -> 0x0015 }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.common.internal.zzp r1 = (com.google.android.gms.common.internal.zzp) r1     // Catch:{ all -> 0x0015 }
            if (r8 != 0) goto L_0x0018
            java.util.concurrent.Executor r8 = r4.zzi     // Catch:{ all -> 0x0015 }
            goto L_0x0018
        L_0x0015:
            r5 = move-exception
            goto L_0x0088
        L_0x0018:
            if (r1 != 0) goto L_0x002c
            com.google.android.gms.common.internal.zzp r1 = new com.google.android.gms.common.internal.zzp     // Catch:{ all -> 0x0015 }
            r1.<init>(r4, r5)     // Catch:{ all -> 0x0015 }
            r1.zze(r6, r6, r7)     // Catch:{ all -> 0x0015 }
            com.google.android.gms.common.ConnectionResult r6 = com.google.android.gms.common.internal.zzp.zzd(r1, r7, r8)     // Catch:{ all -> 0x0015 }
            java.util.HashMap r7 = r4.zzb     // Catch:{ all -> 0x0015 }
            r7.put(r5, r1)     // Catch:{ all -> 0x0015 }
            goto L_0x0059
        L_0x002c:
            android.os.Handler r2 = r4.zzd     // Catch:{ all -> 0x0015 }
            r3 = 0
            r2.removeMessages(r3, r5)     // Catch:{ all -> 0x0015 }
            boolean r2 = r1.zzh(r6)     // Catch:{ all -> 0x0015 }
            if (r2 != 0) goto L_0x006d
            r1.zze(r6, r6, r7)     // Catch:{ all -> 0x0015 }
            int r5 = r1.zza()     // Catch:{ all -> 0x0015 }
            r2 = 1
            r3 = 0
            if (r5 == r2) goto L_0x004d
            r6 = 2
            if (r5 == r6) goto L_0x0048
        L_0x0046:
            r6 = r3
            goto L_0x0059
        L_0x0048:
            com.google.android.gms.common.ConnectionResult r6 = com.google.android.gms.common.internal.zzp.zzd(r1, r7, r8)     // Catch:{ all -> 0x0015 }
            goto L_0x0059
        L_0x004d:
            android.content.ComponentName r5 = r1.zzb()     // Catch:{ all -> 0x0015 }
            android.os.IBinder r7 = r1.zzc()     // Catch:{ all -> 0x0015 }
            r6.onServiceConnected(r5, r7)     // Catch:{ all -> 0x0015 }
            goto L_0x0046
        L_0x0059:
            boolean r5 = r1.zzj()     // Catch:{ all -> 0x0015 }
            if (r5 == 0) goto L_0x0063
            com.google.android.gms.common.ConnectionResult r5 = com.google.android.gms.common.ConnectionResult.RESULT_SUCCESS     // Catch:{ all -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return r5
        L_0x0063:
            if (r6 != 0) goto L_0x006b
            com.google.android.gms.common.ConnectionResult r6 = new com.google.android.gms.common.ConnectionResult     // Catch:{ all -> 0x0015 }
            r5 = -1
            r6.<init>(r5)     // Catch:{ all -> 0x0015 }
        L_0x006b:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            return r6
        L_0x006d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0015 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0015 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0015 }
            r7.<init>()     // Catch:{ all -> 0x0015 }
            java.lang.String r8 = "Trying to bind a GmsServiceConnection that was already connected before.  config="
            r7.append(r8)     // Catch:{ all -> 0x0015 }
            r7.append(r5)     // Catch:{ all -> 0x0015 }
            java.lang.String r5 = r7.toString()     // Catch:{ all -> 0x0015 }
            r6.<init>(r5)     // Catch:{ all -> 0x0015 }
            throw r6     // Catch:{ all -> 0x0015 }
        L_0x0088:
            monitor-exit(r0)     // Catch:{ all -> 0x0015 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zzs.zza(com.google.android.gms.common.internal.zzo, android.content.ServiceConnection, java.lang.String, java.util.concurrent.Executor):com.google.android.gms.common.ConnectionResult");
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzo zzo, ServiceConnection serviceConnection, String str) {
        Preconditions.checkNotNull(serviceConnection, "ServiceConnection must not be null");
        synchronized (this.zzb) {
            try {
                zzp zzp = (zzp) this.zzb.get(zzo);
                if (zzp == null) {
                    String obj = zzo.toString();
                    throw new IllegalStateException("Nonexistent connection status for service config: " + obj);
                } else if (zzp.zzh(serviceConnection)) {
                    zzp.zzf(serviceConnection, str);
                    if (zzp.zzi()) {
                        this.zzd.sendMessageDelayed(this.zzd.obtainMessage(0, zzo), this.zzg);
                    }
                } else {
                    String obj2 = zzo.toString();
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + obj2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzi(Executor executor) {
        synchronized (this.zzb) {
            this.zzi = executor;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzj(Looper looper) {
        synchronized (this.zzb) {
            this.zzd = new zzh(looper, this.zze);
        }
    }
}
