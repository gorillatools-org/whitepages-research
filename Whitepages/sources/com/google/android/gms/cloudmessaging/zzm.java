package com.google.android.gms.cloudmessaging;

import android.os.Handler;

public final /* synthetic */ class zzm implements Handler.Callback {
    public final /* synthetic */ zzp zza;

    public /* synthetic */ zzm(zzp zzp) {
        this.zza = zzp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        r5 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
        if (r5.getBoolean("unsupported", false) == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
        r2.zzc(new com.google.android.gms.cloudmessaging.zzt(4, "Not supported by GmsCore", (java.lang.Throwable) null));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
        r2.zza(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            java.lang.String r0 = "MessengerIpcClient"
            int r1 = r5.arg1
            r2 = 3
            boolean r0 = android.util.Log.isLoggable(r0, r2)
            if (r0 == 0) goto L_0x0021
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Received response to request: "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "MessengerIpcClient"
            android.util.Log.d(r2, r0)
        L_0x0021:
            com.google.android.gms.cloudmessaging.zzp r0 = r4.zza
            monitor-enter(r0)
            android.util.SparseArray r2 = r0.zze     // Catch:{ all -> 0x0046 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0046 }
            com.google.android.gms.cloudmessaging.zzs r2 = (com.google.android.gms.cloudmessaging.zzs) r2     // Catch:{ all -> 0x0046 }
            if (r2 != 0) goto L_0x0048
            java.lang.String r5 = "MessengerIpcClient"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0046 }
            r2.<init>()     // Catch:{ all -> 0x0046 }
            java.lang.String r3 = "Received response for unknown request: "
            r2.append(r3)     // Catch:{ all -> 0x0046 }
            r2.append(r1)     // Catch:{ all -> 0x0046 }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x0046 }
            android.util.Log.w(r5, r1)     // Catch:{ all -> 0x0046 }
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            goto L_0x006e
        L_0x0046:
            r5 = move-exception
            goto L_0x0070
        L_0x0048:
            android.util.SparseArray r3 = r0.zze     // Catch:{ all -> 0x0046 }
            r3.remove(r1)     // Catch:{ all -> 0x0046 }
            r0.zzf()     // Catch:{ all -> 0x0046 }
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            android.os.Bundle r5 = r5.getData()
            java.lang.String r0 = "unsupported"
            r1 = 0
            boolean r0 = r5.getBoolean(r0, r1)
            if (r0 == 0) goto L_0x006b
            java.lang.String r5 = "Not supported by GmsCore"
            com.google.android.gms.cloudmessaging.zzt r0 = new com.google.android.gms.cloudmessaging.zzt
            r1 = 4
            r3 = 0
            r0.<init>(r1, r5, r3)
            r2.zzc(r0)
            goto L_0x006e
        L_0x006b:
            r2.zza(r5)
        L_0x006e:
            r5 = 1
            return r5
        L_0x0070:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cloudmessaging.zzm.handleMessage(android.os.Message):boolean");
    }
}
