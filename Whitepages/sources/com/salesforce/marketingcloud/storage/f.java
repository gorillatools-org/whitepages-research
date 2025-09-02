package com.salesforce.marketingcloud.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.salesforce.marketingcloud.internal.g;
import com.salesforce.marketingcloud.internal.l;
import java.io.File;

public class f {
    final Object a = new Object();
    private final Object b = new Object();
    private final Context c;
    private final SharedPreferences d;
    final File e;
    private final String f;
    private final l g;
    private String h;
    private boolean i;

    class a extends Thread {
        a(String str) {
            super(str);
        }

        public void run() {
            f.this.b();
        }
    }

    class b extends g {
        final /* synthetic */ String b;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, String str2) {
            super(str, objArr);
            this.b = str2;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(9:14|13|17|18|19|20|21|22|23) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
            r5 = r2;
            r2 = r1;
            r1 = r5;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0037 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a() {
            /*
                r6 = this;
                com.salesforce.marketingcloud.storage.f r0 = com.salesforce.marketingcloud.storage.f.this
                java.lang.Object r0 = r0.a
                monitor-enter(r0)
                r1 = 0
                java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0037 }
                com.salesforce.marketingcloud.storage.f r3 = com.salesforce.marketingcloud.storage.f.this     // Catch:{ Exception -> 0x0037 }
                java.io.File r3 = r3.e     // Catch:{ Exception -> 0x0037 }
                r2.<init>(r3)     // Catch:{ Exception -> 0x0037 }
                java.lang.String r1 = r6.b     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                if (r1 == 0) goto L_0x001c
                java.nio.charset.Charset r3 = com.salesforce.marketingcloud.util.l.b     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                byte[] r1 = r1.getBytes(r3)     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                goto L_0x001f
            L_0x001a:
                r1 = move-exception
                goto L_0x0052
            L_0x001c:
                r1 = 0
                byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0030, all -> 0x001a }
            L_0x001f:
                r2.write(r1)     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                java.lang.String r1 = com.salesforce.marketingcloud.storage.e.c     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                java.lang.String r3 = "Gdpr mode [%s] written to file."
                java.lang.String r4 = r6.b     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                com.salesforce.marketingcloud.g.d((java.lang.String) r1, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ Exception -> 0x0030, all -> 0x001a }
                goto L_0x004b
            L_0x0030:
                r1 = r2
                goto L_0x0037
            L_0x0032:
                r2 = move-exception
                r5 = r2
                r2 = r1
                r1 = r5
                goto L_0x0052
            L_0x0037:
                java.lang.String r2 = com.salesforce.marketingcloud.storage.e.c     // Catch:{ all -> 0x0032 }
                java.lang.String r3 = "Failed to write gdpr mode to file: "
                com.salesforce.marketingcloud.storage.f r4 = com.salesforce.marketingcloud.storage.f.this     // Catch:{ all -> 0x0032 }
                java.io.File r4 = r4.e     // Catch:{ all -> 0x0032 }
                java.lang.String r4 = r4.getAbsolutePath()     // Catch:{ all -> 0x0032 }
                java.lang.Object[] r4 = new java.lang.Object[]{r4}     // Catch:{ all -> 0x0032 }
                com.salesforce.marketingcloud.g.b((java.lang.String) r2, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0032 }
                r2 = r1
            L_0x004b:
                com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r2)     // Catch:{ all -> 0x0050 }
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                return
            L_0x0050:
                r1 = move-exception
                goto L_0x0056
            L_0x0052:
                com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r2)     // Catch:{ all -> 0x0050 }
                throw r1     // Catch:{ all -> 0x0050 }
            L_0x0056:
                monitor-exit(r0)     // Catch:{ all -> 0x0050 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.f.b.a():void");
        }
    }

    f(Context context, SharedPreferences sharedPreferences, String str, l lVar) {
        this.c = context;
        this.d = sharedPreferences;
        this.g = lVar;
        this.i = false;
        String str2 = str + "_SFMC_PrivacyMode";
        this.f = str2;
        this.e = new File(context.getNoBackupFilesDir(), str2);
        c();
    }

    private void a() {
        while (!this.i) {
            try {
                this.b.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    private void c() {
        synchronized (this.b) {
            this.i = false;
        }
        new a("gdpr_file_load").start();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x000d, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        if (r5.e.exists() == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0016, code lost:
        r0 = a(r5.e);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (android.text.TextUtils.isEmpty(r0) == false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        r0 = com.salesforce.marketingcloud.storage.e.c;
        com.salesforce.marketingcloud.g.a(r0, "Checking SharedPreferences for gdpr mode", new java.lang.Object[0]);
        r1 = r5.d.getString("cc_state", (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (r1 == null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r5.d.edit().remove("cc_state").apply();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0049, code lost:
        com.salesforce.marketingcloud.g.a(r0, "Checking pre-lollipop location for gdpr mode", new java.lang.Object[0]);
        r0 = new java.io.File(r5.c.getFilesDir(), r5.f);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        if (r0.exists() == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
        r1 = a(r0);
        com.salesforce.marketingcloud.util.g.b(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006a, code lost:
        c(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        r2 = r5.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006f, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r5.h = r1;
        r5.i = true;
        r5.b.notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007a, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.b
            monitor-enter(r0)
            boolean r1 = r5.i     // Catch:{ all -> 0x0009 }
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            return
        L_0x0009:
            r1 = move-exception
            goto L_0x007f
        L_0x000c:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            java.io.File r0 = r5.e
            boolean r0 = r0.exists()
            r1 = 0
            if (r0 == 0) goto L_0x0025
            java.io.File r0 = r5.e
            java.lang.String r0 = a((java.io.File) r0)
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x0023
            goto L_0x006d
        L_0x0023:
            r1 = r0
            goto L_0x006d
        L_0x0025:
            java.lang.String r0 = com.salesforce.marketingcloud.storage.e.c
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.String r4 = "Checking SharedPreferences for gdpr mode"
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r4, (java.lang.Object[]) r3)
            android.content.SharedPreferences r3 = r5.d
            java.lang.String r4 = "cc_state"
            java.lang.String r1 = r3.getString(r4, r1)
            if (r1 == 0) goto L_0x0049
            android.content.SharedPreferences r0 = r5.d
            android.content.SharedPreferences$Editor r0 = r0.edit()
            java.lang.String r2 = "cc_state"
            android.content.SharedPreferences$Editor r0 = r0.remove(r2)
            r0.apply()
            goto L_0x006a
        L_0x0049:
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "Checking pre-lollipop location for gdpr mode"
            com.salesforce.marketingcloud.g.a((java.lang.String) r0, (java.lang.String) r3, (java.lang.Object[]) r2)
            java.io.File r0 = new java.io.File
            android.content.Context r2 = r5.c
            java.io.File r2 = r2.getFilesDir()
            java.lang.String r3 = r5.f
            r0.<init>(r2, r3)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x006a
            java.lang.String r1 = a((java.io.File) r0)
            com.salesforce.marketingcloud.util.g.b(r0)
        L_0x006a:
            r5.c(r1)
        L_0x006d:
            java.lang.Object r2 = r5.b
            monitor-enter(r2)
            r5.h = r1     // Catch:{ all -> 0x007c }
            r0 = 1
            r5.i = r0     // Catch:{ all -> 0x007c }
            java.lang.Object r0 = r5.b     // Catch:{ all -> 0x007c }
            r0.notifyAll()     // Catch:{ all -> 0x007c }
            monitor-exit(r2)     // Catch:{ all -> 0x007c }
            return
        L_0x007c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x007c }
            throw r0
        L_0x007f:
            monitor-exit(r0)     // Catch:{ all -> 0x0009 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.f.b():void");
    }

    public String a(String str) {
        synchronized (this.b) {
            a();
            String str2 = this.h;
            if (str2 != null) {
                str = str2;
            }
        }
        return str;
    }

    public void b(String str) {
        synchronized (this.b) {
            com.salesforce.marketingcloud.g.d(e.c, "Updating gdpr mode: %s", str);
            this.h = str;
            c(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        this.g.b().execute(new b("storing_gdpr", new Object[0], str));
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
            java.lang.String r2 = com.salesforce.marketingcloud.storage.e.c     // Catch:{ all -> 0x002f }
            java.lang.String r3 = "Failed to read gdpr mode from file: "
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
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.storage.f.a(java.io.File):java.lang.String");
    }
}
