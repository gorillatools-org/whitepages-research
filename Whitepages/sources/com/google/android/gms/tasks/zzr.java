package com.google.android.gms.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

final class zzr {
    private final Object zza = new Object();
    private Queue zzb;
    private boolean zzc;

    zzr() {
    }

    public final void zza(zzq zzq) {
        synchronized (this.zza) {
            try {
                if (this.zzb == null) {
                    this.zzb = new ArrayDeque();
                }
                this.zzb.add(zzq);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        r1 = r2.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0 = (com.google.android.gms.tasks.zzq) r2.zzb.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        r2.zzc = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0025, code lost:
        r0.zzd(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002a, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(com.google.android.gms.tasks.Task r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.zza
            monitor-enter(r0)
            java.util.Queue r1 = r2.zzb     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x002d
            boolean r1 = r2.zzc     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x000c
            goto L_0x002d
        L_0x000c:
            r1 = 1
            r2.zzc = r1     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
        L_0x0010:
            java.lang.Object r1 = r2.zza
            monitor-enter(r1)
            java.util.Queue r0 = r2.zzb     // Catch:{ all -> 0x0022 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0022 }
            com.google.android.gms.tasks.zzq r0 = (com.google.android.gms.tasks.zzq) r0     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0024
            r3 = 0
            r2.zzc = r3     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r3 = move-exception
            goto L_0x0029
        L_0x0024:
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            r0.zzd(r3)
            goto L_0x0010
        L_0x0029:
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            throw r3
        L_0x002b:
            r3 = move-exception
            goto L_0x002f
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tasks.zzr.zzb(com.google.android.gms.tasks.Task):void");
    }
}
