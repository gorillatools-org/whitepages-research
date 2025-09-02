package com.google.android.gms.dynamite;

public final class zzb {
    private static ClassLoader zza;
    private static Thread zzb;

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a3, code lost:
        if (r1 == null) goto L_0x00d0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.ClassLoader zza() {
        /*
            java.lang.Class<com.google.android.gms.dynamite.zzb> r0 = com.google.android.gms.dynamite.zzb.class
            monitor-enter(r0)
            java.lang.ClassLoader r1 = zza     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x00d5
            java.lang.Thread r1 = zzb     // Catch:{ all -> 0x00a6 }
            r2 = 0
            if (r1 != 0) goto L_0x00aa
            android.os.Looper r1 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x00a6 }
            java.lang.Thread r1 = r1.getThread()     // Catch:{ all -> 0x00a6 }
            java.lang.ThreadGroup r1 = r1.getThreadGroup()     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x001d
            r1 = r2
            goto L_0x00a1
        L_0x001d:
            java.lang.Class<java.lang.Void> r3 = java.lang.Void.class
            monitor-enter(r3)     // Catch:{ all -> 0x00a6 }
            int r4 = r1.activeGroupCount()     // Catch:{ SecurityException -> 0x0042 }
            java.lang.ThreadGroup[] r5 = new java.lang.ThreadGroup[r4]     // Catch:{ SecurityException -> 0x0042 }
            r1.enumerate(r5)     // Catch:{ SecurityException -> 0x0042 }
            r6 = 0
            r7 = r6
        L_0x002b:
            if (r7 >= r4) goto L_0x0044
            r8 = r5[r7]     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r9 = "dynamiteLoader"
            java.lang.String r10 = r8.getName()     // Catch:{ SecurityException -> 0x0042 }
            boolean r9 = r9.equals(r10)     // Catch:{ SecurityException -> 0x0042 }
            if (r9 == 0) goto L_0x003c
            goto L_0x0045
        L_0x003c:
            int r7 = r7 + 1
            goto L_0x002b
        L_0x003f:
            r1 = move-exception
            goto L_0x00a8
        L_0x0042:
            r1 = move-exception
            goto L_0x0083
        L_0x0044:
            r8 = r2
        L_0x0045:
            if (r8 != 0) goto L_0x004e
            java.lang.ThreadGroup r8 = new java.lang.ThreadGroup     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r4 = "dynamiteLoader"
            r8.<init>(r1, r4)     // Catch:{ SecurityException -> 0x0042 }
        L_0x004e:
            int r1 = r8.activeCount()     // Catch:{ SecurityException -> 0x0042 }
            java.lang.Thread[] r4 = new java.lang.Thread[r1]     // Catch:{ SecurityException -> 0x0042 }
            r8.enumerate(r4)     // Catch:{ SecurityException -> 0x0042 }
        L_0x0057:
            if (r6 >= r1) goto L_0x006b
            r5 = r4[r6]     // Catch:{ SecurityException -> 0x0042 }
            java.lang.String r7 = "GmsDynamite"
            java.lang.String r9 = r5.getName()     // Catch:{ SecurityException -> 0x0042 }
            boolean r7 = r7.equals(r9)     // Catch:{ SecurityException -> 0x0042 }
            if (r7 == 0) goto L_0x0068
            goto L_0x006c
        L_0x0068:
            int r6 = r6 + 1
            goto L_0x0057
        L_0x006b:
            r5 = r2
        L_0x006c:
            if (r5 != 0) goto L_0x009f
            com.google.android.gms.dynamite.zza r1 = new com.google.android.gms.dynamite.zza     // Catch:{ SecurityException -> 0x0080 }
            java.lang.String r4 = "GmsDynamite"
            r1.<init>(r8, r4)     // Catch:{ SecurityException -> 0x0080 }
            r1.setContextClassLoader(r2)     // Catch:{ SecurityException -> 0x007d }
            r1.start()     // Catch:{ SecurityException -> 0x007d }
            r5 = r1
            goto L_0x009f
        L_0x007d:
            r4 = move-exception
            r5 = r1
            goto L_0x0085
        L_0x0080:
            r1 = move-exception
            r4 = r1
            goto L_0x0085
        L_0x0083:
            r4 = r1
            r5 = r2
        L_0x0085:
            java.lang.String r1 = "DynamiteLoaderV2CL"
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x003f }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x003f }
            r6.<init>()     // Catch:{ all -> 0x003f }
            java.lang.String r7 = "Failed to enumerate thread/threadgroup "
            r6.append(r7)     // Catch:{ all -> 0x003f }
            r6.append(r4)     // Catch:{ all -> 0x003f }
            java.lang.String r4 = r6.toString()     // Catch:{ all -> 0x003f }
            android.util.Log.w(r1, r4)     // Catch:{ all -> 0x003f }
        L_0x009f:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            r1 = r5
        L_0x00a1:
            zzb = r1     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x00aa
            goto L_0x00d0
        L_0x00a6:
            r1 = move-exception
            goto L_0x00d9
        L_0x00a8:
            monitor-exit(r3)     // Catch:{ all -> 0x003f }
            throw r1     // Catch:{ all -> 0x00a6 }
        L_0x00aa:
            monitor-enter(r1)     // Catch:{ all -> 0x00a6 }
            java.lang.Thread r3 = zzb     // Catch:{ SecurityException -> 0x00b4 }
            java.lang.ClassLoader r2 = r3.getContextClassLoader()     // Catch:{ SecurityException -> 0x00b4 }
            goto L_0x00cf
        L_0x00b2:
            r2 = move-exception
            goto L_0x00d3
        L_0x00b4:
            r3 = move-exception
            java.lang.String r4 = "DynamiteLoaderV2CL"
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x00b2 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b2 }
            r5.<init>()     // Catch:{ all -> 0x00b2 }
            java.lang.String r6 = "Failed to get thread context classloader "
            r5.append(r6)     // Catch:{ all -> 0x00b2 }
            r5.append(r3)     // Catch:{ all -> 0x00b2 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x00b2 }
            android.util.Log.w(r4, r3)     // Catch:{ all -> 0x00b2 }
        L_0x00cf:
            monitor-exit(r1)     // Catch:{ all -> 0x00b2 }
        L_0x00d0:
            zza = r2     // Catch:{ all -> 0x00a6 }
            goto L_0x00d5
        L_0x00d3:
            monitor-exit(r1)     // Catch:{ all -> 0x00b2 }
            throw r2     // Catch:{ all -> 0x00a6 }
        L_0x00d5:
            java.lang.ClassLoader r1 = zza     // Catch:{ all -> 0x00a6 }
            monitor-exit(r0)
            return r1
        L_0x00d9:
            monitor-exit(r0)     // Catch:{ all -> 0x00a6 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.zzb.zza():java.lang.ClassLoader");
    }
}
