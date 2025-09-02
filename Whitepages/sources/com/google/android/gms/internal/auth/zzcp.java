package com.google.android.gms.internal.auth;

public final class zzcp {
    private static volatile zzdh zza;

    private zzcp() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:75|76) */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        throw r13;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0168 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.auth.zzdh zza(android.content.Context r13) {
        /*
            java.lang.Class<com.google.android.gms.internal.auth.zzcp> r0 = com.google.android.gms.internal.auth.zzcp.class
            monitor-enter(r0)
            com.google.android.gms.internal.auth.zzdh r1 = zza     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x017f
            java.lang.String r1 = android.os.Build.TYPE     // Catch:{ all -> 0x001c }
            java.lang.String r2 = android.os.Build.TAGS     // Catch:{ all -> 0x001c }
            java.lang.String r3 = "eng"
            boolean r3 = r1.equals(r3)     // Catch:{ all -> 0x001c }
            if (r3 != 0) goto L_0x001f
            java.lang.String r3 = "userdebug"
            boolean r1 = r1.equals(r3)     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x0030
            goto L_0x001f
        L_0x001c:
            r13 = move-exception
            goto L_0x0181
        L_0x001f:
            java.lang.String r1 = "dev-keys"
            boolean r1 = r2.contains(r1)     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x0037
            java.lang.String r1 = "test-keys"
            boolean r1 = r2.contains(r1)     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x0030
            goto L_0x0037
        L_0x0030:
            com.google.android.gms.internal.auth.zzdh r13 = com.google.android.gms.internal.auth.zzdh.zzc()     // Catch:{ all -> 0x001c }
        L_0x0034:
            r1 = r13
            goto L_0x0178
        L_0x0037:
            boolean r1 = com.google.android.gms.internal.auth.zzcc.zzb()     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x0047
            boolean r1 = r13.isDeviceProtectedStorage()     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x0047
            android.content.Context r13 = r13.createDeviceProtectedStorageContext()     // Catch:{ all -> 0x001c }
        L_0x0047:
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads()     // Catch:{ all -> 0x001c }
            android.os.StrictMode.allowThreadDiskWrites()     // Catch:{ all -> 0x0067 }
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch:{ RuntimeException -> 0x006f }
            java.lang.String r4 = "phenotype_hermetic"
            java.io.File r4 = r13.getDir(r4, r2)     // Catch:{ RuntimeException -> 0x006f }
            java.lang.String r5 = "overrides.txt"
            r3.<init>(r4, r5)     // Catch:{ RuntimeException -> 0x006f }
            boolean r4 = r3.exists()     // Catch:{ all -> 0x0067 }
            if (r4 == 0) goto L_0x006a
            com.google.android.gms.internal.auth.zzdh r3 = com.google.android.gms.internal.auth.zzdh.zzd(r3)     // Catch:{ all -> 0x0067 }
            goto L_0x007b
        L_0x0067:
            r13 = move-exception
            goto L_0x017b
        L_0x006a:
            com.google.android.gms.internal.auth.zzdh r3 = com.google.android.gms.internal.auth.zzdh.zzc()     // Catch:{ all -> 0x0067 }
            goto L_0x007b
        L_0x006f:
            r3 = move-exception
            java.lang.String r4 = "HermeticFileOverrides"
            java.lang.String r5 = "no data dir"
            android.util.Log.e(r4, r5, r3)     // Catch:{ all -> 0x0067 }
            com.google.android.gms.internal.auth.zzdh r3 = com.google.android.gms.internal.auth.zzdh.zzc()     // Catch:{ all -> 0x0067 }
        L_0x007b:
            boolean r4 = r3.zzb()     // Catch:{ all -> 0x0067 }
            if (r4 == 0) goto L_0x016f
            java.lang.Object r3 = r3.zza()     // Catch:{ all -> 0x0067 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ IOException -> 0x014c }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x014c }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ IOException -> 0x014c }
            r7 = r3
            java.io.File r7 = (java.io.File) r7     // Catch:{ IOException -> 0x014c }
            r6.<init>(r7)     // Catch:{ IOException -> 0x014c }
            r5.<init>(r6)     // Catch:{ IOException -> 0x014c }
            r4.<init>(r5)     // Catch:{ IOException -> 0x014c }
            androidx.collection.SimpleArrayMap r5 = new androidx.collection.SimpleArrayMap     // Catch:{ all -> 0x00c8 }
            r5.<init>()     // Catch:{ all -> 0x00c8 }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ all -> 0x00c8 }
            r6.<init>()     // Catch:{ all -> 0x00c8 }
        L_0x00a1:
            java.lang.String r7 = r4.readLine()     // Catch:{ all -> 0x00c8 }
            if (r7 == 0) goto L_0x0119
            java.lang.String r8 = " "
            r9 = 3
            java.lang.String[] r8 = r7.split(r8, r9)     // Catch:{ all -> 0x00c8 }
            int r10 = r8.length     // Catch:{ all -> 0x00c8 }
            if (r10 == r9) goto L_0x00cb
            java.lang.String r8 = "HermeticFileOverrides"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r9.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r10 = "Invalid: "
            r9.append(r10)     // Catch:{ all -> 0x00c8 }
            r9.append(r7)     // Catch:{ all -> 0x00c8 }
            java.lang.String r7 = r9.toString()     // Catch:{ all -> 0x00c8 }
            android.util.Log.e(r8, r7)     // Catch:{ all -> 0x00c8 }
            goto L_0x00a1
        L_0x00c8:
            r13 = move-exception
            goto L_0x014e
        L_0x00cb:
            r7 = r8[r2]     // Catch:{ all -> 0x00c8 }
            java.lang.String r9 = new java.lang.String     // Catch:{ all -> 0x00c8 }
            r9.<init>(r7)     // Catch:{ all -> 0x00c8 }
            r7 = 1
            r7 = r8[r7]     // Catch:{ all -> 0x00c8 }
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x00c8 }
            r10.<init>(r7)     // Catch:{ all -> 0x00c8 }
            java.lang.String r7 = android.net.Uri.decode(r10)     // Catch:{ all -> 0x00c8 }
            r10 = 2
            r11 = r8[r10]     // Catch:{ all -> 0x00c8 }
            java.lang.Object r11 = r6.get(r11)     // Catch:{ all -> 0x00c8 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x00c8 }
            if (r11 != 0) goto L_0x0101
            r8 = r8[r10]     // Catch:{ all -> 0x00c8 }
            java.lang.String r10 = new java.lang.String     // Catch:{ all -> 0x00c8 }
            r10.<init>(r8)     // Catch:{ all -> 0x00c8 }
            java.lang.String r11 = android.net.Uri.decode(r10)     // Catch:{ all -> 0x00c8 }
            int r8 = r11.length()     // Catch:{ all -> 0x00c8 }
            r12 = 1024(0x400, float:1.435E-42)
            if (r8 < r12) goto L_0x00fe
            if (r11 != r10) goto L_0x0101
        L_0x00fe:
            r6.put(r10, r11)     // Catch:{ all -> 0x00c8 }
        L_0x0101:
            boolean r8 = r5.containsKey(r9)     // Catch:{ all -> 0x00c8 }
            if (r8 != 0) goto L_0x010f
            androidx.collection.SimpleArrayMap r8 = new androidx.collection.SimpleArrayMap     // Catch:{ all -> 0x00c8 }
            r8.<init>()     // Catch:{ all -> 0x00c8 }
            r5.put(r9, r8)     // Catch:{ all -> 0x00c8 }
        L_0x010f:
            java.lang.Object r8 = r5.get(r9)     // Catch:{ all -> 0x00c8 }
            androidx.collection.SimpleArrayMap r8 = (androidx.collection.SimpleArrayMap) r8     // Catch:{ all -> 0x00c8 }
            r8.put(r7, r11)     // Catch:{ all -> 0x00c8 }
            goto L_0x00a1
        L_0x0119:
            java.lang.String r2 = "HermeticFileOverrides"
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00c8 }
            java.lang.String r13 = r13.getPackageName()     // Catch:{ all -> 0x00c8 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c8 }
            r6.<init>()     // Catch:{ all -> 0x00c8 }
            java.lang.String r7 = "Parsed "
            r6.append(r7)     // Catch:{ all -> 0x00c8 }
            r6.append(r3)     // Catch:{ all -> 0x00c8 }
            java.lang.String r3 = " for Android package "
            r6.append(r3)     // Catch:{ all -> 0x00c8 }
            r6.append(r13)     // Catch:{ all -> 0x00c8 }
            java.lang.String r13 = r6.toString()     // Catch:{ all -> 0x00c8 }
            android.util.Log.w(r2, r13)     // Catch:{ all -> 0x00c8 }
            com.google.android.gms.internal.auth.zzci r13 = new com.google.android.gms.internal.auth.zzci     // Catch:{ all -> 0x00c8 }
            r13.<init>(r5)     // Catch:{ all -> 0x00c8 }
            r4.close()     // Catch:{ IOException -> 0x014c }
            com.google.android.gms.internal.auth.zzdh r13 = com.google.android.gms.internal.auth.zzdh.zzd(r13)     // Catch:{ all -> 0x0067 }
            goto L_0x0173
        L_0x014c:
            r13 = move-exception
            goto L_0x0169
        L_0x014e:
            r4.close()     // Catch:{ all -> 0x0152 }
            goto L_0x0168
        L_0x0152:
            r2 = move-exception
            java.lang.Class<java.lang.Throwable> r3 = java.lang.Throwable.class
            java.lang.String r4 = "addSuppressed"
            java.lang.Class<java.lang.Throwable> r5 = java.lang.Throwable.class
            java.lang.Class[] r5 = new java.lang.Class[]{r5}     // Catch:{ Exception -> 0x0168 }
            java.lang.reflect.Method r3 = r3.getDeclaredMethod(r4, r5)     // Catch:{ Exception -> 0x0168 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch:{ Exception -> 0x0168 }
            r3.invoke(r13, r2)     // Catch:{ Exception -> 0x0168 }
        L_0x0168:
            throw r13     // Catch:{ IOException -> 0x014c }
        L_0x0169:
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x0067 }
            r2.<init>(r13)     // Catch:{ all -> 0x0067 }
            throw r2     // Catch:{ all -> 0x0067 }
        L_0x016f:
            com.google.android.gms.internal.auth.zzdh r13 = com.google.android.gms.internal.auth.zzdh.zzc()     // Catch:{ all -> 0x0067 }
        L_0x0173:
            android.os.StrictMode.setThreadPolicy(r1)     // Catch:{ all -> 0x001c }
            goto L_0x0034
        L_0x0178:
            zza = r1     // Catch:{ all -> 0x001c }
            goto L_0x017f
        L_0x017b:
            android.os.StrictMode.setThreadPolicy(r1)     // Catch:{ all -> 0x001c }
            throw r13     // Catch:{ all -> 0x001c }
        L_0x017f:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return r1
        L_0x0181:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.auth.zzcp.zza(android.content.Context):com.google.android.gms.internal.auth.zzdh");
    }
}
