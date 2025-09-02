package com.google.android.gms.common.util;

import android.os.Process;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.common.zzab;
import com.google.android.gms.internal.common.zzac;
import com.google.android.gms.internal.common.zzj;
import com.google.android.gms.internal.common.zzl;

@KeepForSdk
public class ProcessUtils {
    private static String zza;
    private static int zzb;
    private static Boolean zzc;

    private ProcessUtils() {
    }

    public static boolean zza() {
        Boolean bool = zzc;
        if (bool == null) {
            if (PlatformVersion.isAtLeastP()) {
                bool = Boolean.valueOf(Process.isIsolated());
            } else {
                try {
                    Object zza2 = zzl.zza(Process.class, "isIsolated", new zzj[0]);
                    Object[] objArr = new Object[0];
                    if (zza2 != null) {
                        bool = (Boolean) zza2;
                    } else {
                        throw new zzac(zzab.zza("expected a non-null reference", objArr));
                    }
                } catch (ReflectiveOperationException unused) {
                    bool = Boolean.FALSE;
                }
            }
            zzc = bool;
        }
        return bool.booleanValue();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMyProcessName() {
        /*
            java.lang.String r0 = zza
            if (r0 != 0) goto L_0x0067
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x0011
            java.lang.String r0 = android.app.Application.getProcessName()
            zza = r0
            goto L_0x0067
        L_0x0011:
            int r0 = zzb
            if (r0 != 0) goto L_0x001b
            int r0 = android.os.Process.myPid()
            zzb = r0
        L_0x001b:
            r1 = 0
            if (r0 > 0) goto L_0x001f
            goto L_0x0065
        L_0x001f:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            r2.<init>()     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            r2.append(r0)     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            java.lang.String r0 = "/cmdline"
            r2.append(r0)     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            java.lang.String r0 = r2.toString()     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            android.os.StrictMode$ThreadPolicy r2 = android.os.StrictMode.allowThreadDiskReads()     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x005a }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ all -> 0x005a }
            r4.<init>(r0)     // Catch:{ all -> 0x005a }
            r3.<init>(r4)     // Catch:{ all -> 0x005a }
            android.os.StrictMode.setThreadPolicy(r2)     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            java.lang.String r0 = r3.readLine()     // Catch:{ IOException -> 0x0051, all -> 0x0055 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ IOException -> 0x0051, all -> 0x0055 }
            java.lang.String r1 = r0.trim()     // Catch:{ IOException -> 0x0051, all -> 0x0055 }
        L_0x0051:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
            goto L_0x0065
        L_0x0055:
            r0 = move-exception
            r1 = r3
            goto L_0x005f
        L_0x0058:
            r0 = move-exception
            goto L_0x005f
        L_0x005a:
            r0 = move-exception
            android.os.StrictMode.setThreadPolicy(r2)     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
            throw r0     // Catch:{ IOException -> 0x0063, all -> 0x0058 }
        L_0x005f:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r1)
            throw r0
        L_0x0063:
            r3 = r1
            goto L_0x0051
        L_0x0065:
            zza = r1
        L_0x0067:
            java.lang.String r0 = zza
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.ProcessUtils.getMyProcessName():java.lang.String");
    }
}
