package com.google.android.gms.internal.gcm;

import java.io.PrintStream;

public final class zzq {
    private static final zzr zzdq;
    private static final int zzdr;

    static final class zzd extends zzr {
        zzd() {
        }

        public final void zzd(Throwable th, Throwable th2) {
        }
    }

    public static void zzd(Throwable th, Throwable th2) {
        zzdq.zzd(th, th2);
    }

    private static Integer zzac() {
        try {
            return (Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get((Object) null);
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(printStream);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0061  */
    static {
        /*
            java.lang.Integer r0 = zzac()     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0016
            int r1 = r0.intValue()     // Catch:{ all -> 0x0014 }
            r2 = 19
            if (r1 < r2) goto L_0x0016
            com.google.android.gms.internal.gcm.zzv r1 = new com.google.android.gms.internal.gcm.zzv     // Catch:{ all -> 0x0014 }
            r1.<init>()     // Catch:{ all -> 0x0014 }
            goto L_0x005b
        L_0x0014:
            r1 = move-exception
            goto L_0x002c
        L_0x0016:
            java.lang.String r1 = "com.google.devtools.build.android.desugar.runtime.twr_disable_mimic"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0024
            com.google.android.gms.internal.gcm.zzu r1 = new com.google.android.gms.internal.gcm.zzu     // Catch:{ all -> 0x0014 }
            r1.<init>()     // Catch:{ all -> 0x0014 }
            goto L_0x005b
        L_0x0024:
            com.google.android.gms.internal.gcm.zzq$zzd r1 = new com.google.android.gms.internal.gcm.zzq$zzd     // Catch:{ all -> 0x0014 }
            r1.<init>()     // Catch:{ all -> 0x0014 }
            goto L_0x005b
        L_0x002a:
            r1 = move-exception
            r0 = 0
        L_0x002c:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.Class<com.google.android.gms.internal.gcm.zzq$zzd> r3 = com.google.android.gms.internal.gcm.zzq.zzd.class
            java.lang.String r3 = r3.getName()
            int r4 = r3.length()
            int r4 = r4 + 133
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy "
            r5.append(r4)
            r5.append(r3)
            java.lang.String r3 = "will be used. The error is: "
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            r2.println(r3)
            r1.printStackTrace(r2)
            com.google.android.gms.internal.gcm.zzq$zzd r1 = new com.google.android.gms.internal.gcm.zzq$zzd
            r1.<init>()
        L_0x005b:
            zzdq = r1
            if (r0 != 0) goto L_0x0061
            r0 = 1
            goto L_0x0065
        L_0x0061:
            int r0 = r0.intValue()
        L_0x0065:
            zzdr = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gcm.zzq.<clinit>():void");
    }
}
