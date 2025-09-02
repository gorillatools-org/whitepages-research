package com.google.android.gms.common.wrappers;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InstantApps {
    private static Context zza;
    private static Boolean zzb;

    /* JADX WARNING: Can't wrap try/catch for region: R(4:18|19|20|21) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0040 */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean isInstantApp(android.content.Context r4) {
        /*
            java.lang.Class<com.google.android.gms.common.wrappers.InstantApps> r0 = com.google.android.gms.common.wrappers.InstantApps.class
            monitor-enter(r0)
            android.content.Context r1 = r4.getApplicationContext()     // Catch:{ all -> 0x0018 }
            android.content.Context r2 = zza     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x001a
            java.lang.Boolean r3 = zzb     // Catch:{ all -> 0x0018 }
            if (r3 == 0) goto L_0x001a
            if (r2 == r1) goto L_0x0012
            goto L_0x001a
        L_0x0012:
            boolean r4 = r3.booleanValue()     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r4
        L_0x0018:
            r4 = move-exception
            goto L_0x004e
        L_0x001a:
            r2 = 0
            zzb = r2     // Catch:{ all -> 0x0018 }
            boolean r2 = com.google.android.gms.common.util.PlatformVersion.isAtLeastO()     // Catch:{ all -> 0x0018 }
            if (r2 == 0) goto L_0x0032
            android.content.pm.PackageManager r4 = r1.getPackageManager()     // Catch:{ all -> 0x0018 }
            boolean r4 = r4.isInstantApp()     // Catch:{ all -> 0x0018 }
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0018 }
            zzb = r4     // Catch:{ all -> 0x0018 }
            goto L_0x0044
        L_0x0032:
            java.lang.ClassLoader r4 = r4.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0040 }
            java.lang.String r2 = "com.google.android.instantapps.supervisor.InstantAppsRuntime"
            r4.loadClass(r2)     // Catch:{ ClassNotFoundException -> 0x0040 }
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch:{ ClassNotFoundException -> 0x0040 }
            zzb = r4     // Catch:{ ClassNotFoundException -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            java.lang.Boolean r4 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0018 }
            zzb = r4     // Catch:{ all -> 0x0018 }
        L_0x0044:
            zza = r1     // Catch:{ all -> 0x0018 }
            java.lang.Boolean r4 = zzb     // Catch:{ all -> 0x0018 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r4
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x0018 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.wrappers.InstantApps.isInstantApp(android.content.Context):boolean");
    }
}
