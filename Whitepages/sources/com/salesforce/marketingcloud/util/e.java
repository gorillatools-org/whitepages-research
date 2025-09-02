package com.salesforce.marketingcloud.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.salesforce.marketingcloud.g;
import java.io.File;
import java.util.Locale;
import java.util.UUID;

public final class e {
    private static final String a = g.a("DeviceData");
    private static final String b = "SFMCDeviceUUID";
    static volatile String c;

    private e() {
    }

    private static String a() {
        return l.a(String.format(Locale.ENGLISH, "%s%d", new Object[]{UUID.randomUUID().toString(), Long.valueOf(System.currentTimeMillis())}));
    }

    private static String b(Context context, String str) {
        File file = new File(context.getNoBackupFilesDir(), b);
        String a2 = file.exists() ? a(file) : null;
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        String str2 = a;
        g.a(str2, "Checking SharedPreferences for deviceId", new Object[0]);
        String a3 = a(context);
        if (TextUtils.isEmpty(a3)) {
            g.a(str2, "Checking pre-lollipop location for deviceId", new Object[0]);
            File file2 = new File(context.getFilesDir(), b);
            if (file2.exists()) {
                a3 = a(file2);
                g.b(file2);
            }
        }
        if (TextUtils.isEmpty(a3)) {
            Object[] objArr = new Object[0];
            if (str != null) {
                g.a(str2, "Using registrationId as deviceId", objArr);
            } else {
                g.a(str2, "Generating/Storing new deviceId", objArr);
                str = a();
            }
        } else {
            str = a3;
        }
        a(file, str);
        return str;
    }

    public static String a(Context context, String str) {
        if (c == null) {
            synchronized (e.class) {
                c = b(context, str);
            }
        }
        return c;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x001c */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.io.File r5) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x001b, all -> 0x0019 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            java.nio.charset.Charset r4 = com.salesforce.marketingcloud.util.l.b     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            r3.<init>(r1, r4)     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Exception -> 0x001c, all -> 0x0017 }
            goto L_0x002b
        L_0x0017:
            r5 = move-exception
            goto L_0x0032
        L_0x0019:
            r5 = move-exception
            goto L_0x0031
        L_0x001b:
            r1 = r0
        L_0x001c:
            java.lang.String r2 = a     // Catch:{ all -> 0x002f }
            java.lang.String r3 = "Failed to read device id from file: "
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ all -> 0x002f }
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch:{ all -> 0x002f }
            com.salesforce.marketingcloud.g.b((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r5)     // Catch:{ all -> 0x002f }
        L_0x002b:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            return r0
        L_0x002f:
            r5 = move-exception
            r0 = r1
        L_0x0031:
            r1 = r0
        L_0x0032:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.e.a(java.io.File):java.lang.String");
    }

    private static String a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("_et_default_shared_preferences", 0);
        String string = sharedPreferences.getString("id", (String) null);
        if (string != null) {
            sharedPreferences.edit().remove("id").apply();
        }
        return string;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:9|10|11) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        com.salesforce.marketingcloud.g.b(a, "Failed to write device id to file: ", r2.getAbsolutePath());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        r2 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r2, java.lang.String r3) {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0016 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0016 }
            java.nio.charset.Charset r0 = com.salesforce.marketingcloud.util.l.b     // Catch:{ Exception -> 0x0012, all -> 0x0010 }
            byte[] r3 = r3.getBytes(r0)     // Catch:{ Exception -> 0x0012, all -> 0x0010 }
            r1.write(r3)     // Catch:{ Exception -> 0x0012, all -> 0x0010 }
            goto L_0x0026
        L_0x0010:
            r2 = move-exception
            goto L_0x002b
        L_0x0012:
            r0 = r1
            goto L_0x0016
        L_0x0014:
            r2 = move-exception
            goto L_0x002a
        L_0x0016:
            java.lang.String r3 = a     // Catch:{ all -> 0x0014 }
            java.lang.String r1 = "Failed to write device id to file: "
            java.lang.String r2 = r2.getAbsolutePath()     // Catch:{ all -> 0x0014 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2}     // Catch:{ all -> 0x0014 }
            com.salesforce.marketingcloud.g.b((java.lang.String) r3, (java.lang.String) r1, (java.lang.Object[]) r2)     // Catch:{ all -> 0x0014 }
            r1 = r0
        L_0x0026:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            return
        L_0x002a:
            r1 = r0
        L_0x002b:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.e.a(java.io.File, java.lang.String):void");
    }
}
