package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.g;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@SuppressLint({"UnknownNullness"})
public final class f implements Closeable {
    private static final String A = "READ";
    static final String o = "journal";
    static final String p = "journal.tmp";
    static final String q = "journal.bkp";
    static final String r = "libcore.io.DiskLruCache";
    static final String s = "1";
    static final long t = -1;
    static final String u = "[a-z0-9_-]{1,120}";
    static final Pattern v = Pattern.compile(u);
    static final OutputStream w = new a();
    private static final String x = "CLEAN";
    private static final String y = "DIRTY";
    private static final String z = "REMOVE";
    final ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
    final File b;
    final int c;
    private final File d;
    private final File e;
    private final File f;
    private final int g;
    private final LinkedHashMap<String, d> h = new LinkedHashMap<>(0, 0.75f, true);
    Writer i;
    int j;
    private long k;
    private long l;
    private long m;

    /* renamed from: n  reason: collision with root package name */
    private final Callable<Void> f44n = new b();

    class a extends OutputStream {
        a() {
        }

        public void write(int i) throws IOException {
        }
    }

    class b implements Callable<Void> {
        b() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
            return null;
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Void call() throws java.lang.Exception {
            /*
                r4 = this;
                com.salesforce.marketingcloud.util.f r0 = com.salesforce.marketingcloud.util.f.this
                monitor-enter(r0)
                com.salesforce.marketingcloud.util.f r1 = com.salesforce.marketingcloud.util.f.this     // Catch:{ all -> 0x000c }
                java.io.Writer r2 = r1.i     // Catch:{ all -> 0x000c }
                r3 = 0
                if (r2 != 0) goto L_0x000e
                monitor-exit(r0)     // Catch:{ all -> 0x000c }
                return r3
            L_0x000c:
                r1 = move-exception
                goto L_0x0025
            L_0x000e:
                r1.l()     // Catch:{ all -> 0x000c }
                com.salesforce.marketingcloud.util.f r1 = com.salesforce.marketingcloud.util.f.this     // Catch:{ all -> 0x000c }
                boolean r1 = r1.g()     // Catch:{ all -> 0x000c }
                if (r1 == 0) goto L_0x0023
                com.salesforce.marketingcloud.util.f r1 = com.salesforce.marketingcloud.util.f.this     // Catch:{ all -> 0x000c }
                r1.j()     // Catch:{ all -> 0x000c }
                com.salesforce.marketingcloud.util.f r1 = com.salesforce.marketingcloud.util.f.this     // Catch:{ all -> 0x000c }
                r2 = 0
                r1.j = r2     // Catch:{ all -> 0x000c }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x000c }
                return r3
            L_0x0025:
                monitor-exit(r0)     // Catch:{ all -> 0x000c }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.b.call():java.lang.Void");
        }
    }

    public final class c {
        final d a;
        final boolean[] b;
        boolean c;
        private boolean d;

        private class a extends FilterOutputStream {
            a(OutputStream outputStream) {
                super(outputStream);
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    c.this.c = true;
                }
            }
        }

        c(d dVar) {
            this.a = dVar;
            this.b = dVar.c ? null : new boolean[f.this.c];
        }

        public void a() throws IOException {
            f.this.a(this, false);
        }

        public void b() {
            if (!this.d) {
                try {
                    a();
                } catch (IOException unused) {
                }
            }
        }

        public void c() throws IOException {
            if (this.c) {
                f.this.a(this, false);
                f.this.d(this.a.a);
            } else {
                f.this.a(this, true);
            }
            this.d = true;
        }

        public String a(int i) throws IOException {
            InputStream b2 = b(i);
            if (b2 != null) {
                return f.a(b2);
            }
            return null;
        }

        public InputStream b(int i) throws IOException {
            synchronized (f.this) {
                d dVar = this.a;
                if (dVar.d != this) {
                    throw new IllegalStateException();
                } else if (!dVar.c) {
                    return null;
                } else {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(this.a.a(i));
                        return fileInputStream;
                    } catch (FileNotFoundException unused) {
                        return null;
                    }
                }
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(12:7|(1:9)|12|13|14|15|16|17|18|19|20|21) */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
            return com.salesforce.marketingcloud.util.f.w;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0043, code lost:
            throw r5;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0025 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.io.OutputStream c(int r5) throws java.io.IOException {
            /*
                r4 = this;
                if (r5 < 0) goto L_0x0044
                com.salesforce.marketingcloud.util.f r0 = com.salesforce.marketingcloud.util.f.this
                int r1 = r0.c
                if (r5 >= r1) goto L_0x0044
                monitor-enter(r0)
                com.salesforce.marketingcloud.util.f$d r1 = r4.a     // Catch:{ all -> 0x0019 }
                com.salesforce.marketingcloud.util.f$c r2 = r1.d     // Catch:{ all -> 0x0019 }
                if (r2 != r4) goto L_0x003c
                boolean r2 = r1.c     // Catch:{ all -> 0x0019 }
                if (r2 != 0) goto L_0x001b
                boolean[] r2 = r4.b     // Catch:{ all -> 0x0019 }
                r3 = 1
                r2[r5] = r3     // Catch:{ all -> 0x0019 }
                goto L_0x001b
            L_0x0019:
                r5 = move-exception
                goto L_0x0042
            L_0x001b:
                java.io.File r5 = r1.b((int) r5)     // Catch:{ all -> 0x0019 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0025 }
                r1.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0025 }
                goto L_0x0031
            L_0x0025:
                com.salesforce.marketingcloud.util.f r1 = com.salesforce.marketingcloud.util.f.this     // Catch:{ all -> 0x0019 }
                java.io.File r1 = r1.b     // Catch:{ all -> 0x0019 }
                r1.mkdirs()     // Catch:{ all -> 0x0019 }
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0038 }
                r1.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0038 }
            L_0x0031:
                com.salesforce.marketingcloud.util.f$c$a r5 = new com.salesforce.marketingcloud.util.f$c$a     // Catch:{ all -> 0x0019 }
                r5.<init>(r1)     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                return r5
            L_0x0038:
                java.io.OutputStream r5 = com.salesforce.marketingcloud.util.f.w     // Catch:{ all -> 0x0019 }
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                return r5
            L_0x003c:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0019 }
                r5.<init>()     // Catch:{ all -> 0x0019 }
                throw r5     // Catch:{ all -> 0x0019 }
            L_0x0042:
                monitor-exit(r0)     // Catch:{ all -> 0x0019 }
                throw r5
            L_0x0044:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Expected index "
                r1.append(r2)
                r1.append(r5)
                java.lang.String r5 = " to be greater than 0 and less than the maximum value count of "
                r1.append(r5)
                com.salesforce.marketingcloud.util.f r5 = com.salesforce.marketingcloud.util.f.this
                int r5 = r5.c
                r1.append(r5)
                java.lang.String r5 = r1.toString()
                r0.<init>(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.c.c(int):java.io.OutputStream");
        }

        public void a(int i, String str) throws IOException {
            OutputStreamWriter outputStreamWriter = null;
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(i), g.c);
                try {
                    outputStreamWriter2.write(str);
                    g.a((Closeable) outputStreamWriter2);
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    g.a((Closeable) outputStreamWriter);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                g.a((Closeable) outputStreamWriter);
                throw th;
            }
        }
    }

    private final class d {
        final String a;
        final long[] b;
        boolean c;
        c d;
        long e;

        d(String str) {
            this.a = str;
            this.b = new long[f.this.c];
        }

        public File a(int i) {
            File file = f.this.b;
            return new File(file, this.a + "." + i);
        }

        public File b(int i) {
            File file = f.this.b;
            return new File(file, this.a + "." + i + ".tmp");
        }

        public String a() throws IOException {
            StringBuilder sb = new StringBuilder();
            for (long append : this.b) {
                sb.append(' ');
                sb.append(append);
            }
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public void b(String[] strArr) throws IOException {
            if (strArr.length == f.this.c) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.b[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        throw a(strArr);
                    }
                }
                return;
            }
            throw a(strArr);
        }

        private IOException a(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }
    }

    public final class e implements Closeable {
        private final String a;
        private final long b;
        private final InputStream[] c;
        private final long[] d;

        e(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.a = str;
            this.b = j;
            this.c = inputStreamArr;
            this.d = jArr;
        }

        public c a() throws IOException {
            return f.this.a(this.a, this.b);
        }

        public long b(int i) {
            return this.d[i];
        }

        public String c(int i) throws IOException {
            return f.a(a(i));
        }

        public void close() {
            for (InputStream a2 : this.c) {
                g.a((Closeable) a2);
            }
        }

        public InputStream a(int i) {
            return this.c[i];
        }
    }

    private f(File file, int i2, int i3, long j2) {
        this.b = file;
        this.g = i2;
        this.d = new File(file, o);
        this.e = new File(file, p);
        this.f = new File(file, q);
        this.c = i3;
        this.k = j2;
    }

    private void a() {
        if (this.i == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private void h() throws IOException {
        a(this.e);
        Iterator<d> it = this.h.values().iterator();
        while (it.hasNext()) {
            d next = it.next();
            int i2 = 0;
            if (next.d == null) {
                while (i2 < this.c) {
                    this.l += next.b[i2];
                    i2++;
                }
            } else {
                next.d = null;
                while (i2 < this.c) {
                    a(next.a(i2));
                    a(next.b(i2));
                    i2++;
                }
                it.remove();
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:17|18|(1:20)(1:21)|22|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r9.j = r0 - r9.h.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006e, code lost:
        if (r1.b() != false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0070, code lost:
        j();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        r9.i = new java.io.BufferedWriter(new java.io.OutputStreamWriter(new java.io.FileOutputStream(r9.d, true), com.salesforce.marketingcloud.util.g.a));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008d, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0061 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0061=Splitter:B:17:0x0061, B:24:0x008e=Splitter:B:24:0x008e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() throws java.io.IOException {
        /*
            r9 = this;
            java.lang.String r0 = ", "
            com.salesforce.marketingcloud.util.k r1 = new com.salesforce.marketingcloud.util.k
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.File r3 = r9.d
            r2.<init>(r3)
            java.nio.charset.Charset r3 = com.salesforce.marketingcloud.util.g.a
            r1.<init>((java.io.InputStream) r2, (java.nio.charset.Charset) r3)
            java.lang.String r2 = r1.d()     // Catch:{ all -> 0x005f }
            java.lang.String r3 = r1.d()     // Catch:{ all -> 0x005f }
            java.lang.String r4 = r1.d()     // Catch:{ all -> 0x005f }
            java.lang.String r5 = r1.d()     // Catch:{ all -> 0x005f }
            java.lang.String r6 = r1.d()     // Catch:{ all -> 0x005f }
            java.lang.String r7 = "libcore.io.DiskLruCache"
            boolean r7 = r7.equals(r2)     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x008e
            java.lang.String r7 = "1"
            boolean r7 = r7.equals(r3)     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x008e
            int r7 = r9.g     // Catch:{ all -> 0x005f }
            java.lang.String r7 = java.lang.Integer.toString(r7)     // Catch:{ all -> 0x005f }
            boolean r4 = r7.equals(r4)     // Catch:{ all -> 0x005f }
            if (r4 == 0) goto L_0x008e
            int r4 = r9.c     // Catch:{ all -> 0x005f }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x005f }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x005f }
            if (r4 == 0) goto L_0x008e
            java.lang.String r4 = ""
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x005f }
            if (r4 == 0) goto L_0x008e
            r0 = 0
        L_0x0055:
            java.lang.String r2 = r1.d()     // Catch:{ EOFException -> 0x0061 }
            r9.c(r2)     // Catch:{ EOFException -> 0x0061 }
            int r0 = r0 + 1
            goto L_0x0055
        L_0x005f:
            r0 = move-exception
            goto L_0x00bc
        L_0x0061:
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r2 = r9.h     // Catch:{ all -> 0x005f }
            int r2 = r2.size()     // Catch:{ all -> 0x005f }
            int r0 = r0 - r2
            r9.j = r0     // Catch:{ all -> 0x005f }
            boolean r0 = r1.b()     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0074
            r9.j()     // Catch:{ all -> 0x005f }
            goto L_0x008a
        L_0x0074:
            java.io.BufferedWriter r0 = new java.io.BufferedWriter     // Catch:{ all -> 0x005f }
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch:{ all -> 0x005f }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x005f }
            java.io.File r4 = r9.d     // Catch:{ all -> 0x005f }
            r5 = 1
            r3.<init>(r4, r5)     // Catch:{ all -> 0x005f }
            java.nio.charset.Charset r4 = com.salesforce.marketingcloud.util.g.a     // Catch:{ all -> 0x005f }
            r2.<init>(r3, r4)     // Catch:{ all -> 0x005f }
            r0.<init>(r2)     // Catch:{ all -> 0x005f }
            r9.i = r0     // Catch:{ all -> 0x005f }
        L_0x008a:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            return
        L_0x008e:
            java.io.IOException r4 = new java.io.IOException     // Catch:{ all -> 0x005f }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x005f }
            r7.<init>()     // Catch:{ all -> 0x005f }
            java.lang.String r8 = "unexpected journal header: ["
            r7.append(r8)     // Catch:{ all -> 0x005f }
            r7.append(r2)     // Catch:{ all -> 0x005f }
            r7.append(r0)     // Catch:{ all -> 0x005f }
            r7.append(r3)     // Catch:{ all -> 0x005f }
            r7.append(r0)     // Catch:{ all -> 0x005f }
            r7.append(r5)     // Catch:{ all -> 0x005f }
            r7.append(r0)     // Catch:{ all -> 0x005f }
            r7.append(r6)     // Catch:{ all -> 0x005f }
            java.lang.String r0 = "]"
            r7.append(r0)     // Catch:{ all -> 0x005f }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x005f }
            r4.<init>(r0)     // Catch:{ all -> 0x005f }
            throw r4     // Catch:{ all -> 0x005f }
        L_0x00bc:
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.i():void");
    }

    public void b() throws IOException {
        close();
        g.a(this.b);
    }

    public synchronized void c() throws IOException {
        a();
        l();
        this.i.flush();
    }

    public synchronized void close() throws IOException {
        try {
            if (this.i != null) {
                Iterator it = new ArrayList(this.h.values()).iterator();
                while (it.hasNext()) {
                    c cVar = ((d) it.next()).d;
                    if (cVar != null) {
                        cVar.a();
                    }
                }
                l();
                this.i.close();
                this.i = null;
            }
        } finally {
            while (true) {
            }
        }
    }

    public File d() {
        return this.b;
    }

    public synchronized long e() {
        return this.k;
    }

    public synchronized boolean f() {
        return this.i == null;
    }

    /* access modifiers changed from: package-private */
    public boolean g() {
        int i2 = this.j;
        return i2 >= 2000 && i2 >= this.h.size();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public synchronized void j() throws IOException {
        BufferedWriter bufferedWriter;
        StringBuilder sb;
        try {
            Writer writer = this.i;
            if (writer != null) {
                writer.close();
            }
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.e), g.a));
            bufferedWriter.write(r);
            bufferedWriter.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            bufferedWriter.write(s);
            bufferedWriter.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            bufferedWriter.write(Integer.toString(this.g));
            bufferedWriter.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            bufferedWriter.write(Integer.toString(this.c));
            bufferedWriter.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            bufferedWriter.write(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            for (d next : this.h.values()) {
                if (next.d != null) {
                    sb = new StringBuilder();
                    sb.append("DIRTY ");
                    sb.append(next.a);
                    sb.append(10);
                } else {
                    sb = new StringBuilder();
                    sb.append("CLEAN ");
                    sb.append(next.a);
                    sb.append(next.a());
                    sb.append(10);
                }
                bufferedWriter.write(sb.toString());
            }
            bufferedWriter.close();
            if (this.d.exists()) {
                a(this.d, this.f, true);
            }
            a(this.e, this.d, false);
            this.f.delete();
            this.i = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.d, true), g.a));
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized long k() {
        return this.l;
    }

    /* access modifiers changed from: package-private */
    public void l() throws IOException {
        while (this.l > this.k) {
            d((String) this.h.entrySet().iterator().next().getKey());
        }
    }

    private void c(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i2 = indexOf + 1;
            int indexOf2 = str.indexOf(32, i2);
            if (indexOf2 == -1) {
                str2 = str.substring(i2);
                if (indexOf == 6 && str.startsWith(z)) {
                    this.h.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i2, indexOf2);
            }
            d dVar = this.h.get(str2);
            if (dVar == null) {
                dVar = new d(str2);
                this.h.put(str2, dVar);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith(x)) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                dVar.c = true;
                dVar.d = null;
                dVar.b(split);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith(y)) {
                dVar.d = new c(dVar);
            } else if (indexOf2 != -1 || indexOf != 4 || !str.startsWith(A)) {
                throw new IOException("unexpected journal line: " + str);
            }
        } else {
            throw new IOException("unexpected journal line: " + str);
        }
    }

    private void e(String str) {
        if (!v.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00f5, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.salesforce.marketingcloud.util.f.c r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.salesforce.marketingcloud.util.f$d r0 = r10.a     // Catch:{ all -> 0x0028 }
            com.salesforce.marketingcloud.util.f$c r1 = r0.d     // Catch:{ all -> 0x0028 }
            if (r1 != r10) goto L_0x00f6
            r1 = 0
            if (r11 == 0) goto L_0x0048
            boolean r2 = r0.c     // Catch:{ all -> 0x0028 }
            if (r2 != 0) goto L_0x0048
            r2 = r1
        L_0x000f:
            int r3 = r9.c     // Catch:{ all -> 0x0028 }
            if (r2 >= r3) goto L_0x0048
            boolean[] r3 = r10.b     // Catch:{ all -> 0x0028 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0028 }
            if (r3 == 0) goto L_0x002e
            java.io.File r3 = r0.b((int) r2)     // Catch:{ all -> 0x0028 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0028 }
            if (r3 != 0) goto L_0x002b
            r10.a()     // Catch:{ all -> 0x0028 }
            monitor-exit(r9)
            return
        L_0x0028:
            r10 = move-exception
            goto L_0x00fc
        L_0x002b:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x002e:
            r10.a()     // Catch:{ all -> 0x0028 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0028 }
            r11.<init>()     // Catch:{ all -> 0x0028 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x0028 }
            r11.append(r2)     // Catch:{ all -> 0x0028 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0028 }
            r10.<init>(r11)     // Catch:{ all -> 0x0028 }
            throw r10     // Catch:{ all -> 0x0028 }
        L_0x0048:
            int r10 = r9.c     // Catch:{ all -> 0x0028 }
            if (r1 >= r10) goto L_0x0078
            java.io.File r10 = r0.b((int) r1)     // Catch:{ all -> 0x0028 }
            if (r11 == 0) goto L_0x0072
            boolean r2 = r10.exists()     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0075
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0028 }
            r10.renameTo(r2)     // Catch:{ all -> 0x0028 }
            long[] r10 = r0.b     // Catch:{ all -> 0x0028 }
            r3 = r10[r1]     // Catch:{ all -> 0x0028 }
            long r5 = r2.length()     // Catch:{ all -> 0x0028 }
            long[] r10 = r0.b     // Catch:{ all -> 0x0028 }
            r10[r1] = r5     // Catch:{ all -> 0x0028 }
            long r7 = r9.l     // Catch:{ all -> 0x0028 }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.l = r7     // Catch:{ all -> 0x0028 }
            goto L_0x0075
        L_0x0072:
            a((java.io.File) r10)     // Catch:{ all -> 0x0028 }
        L_0x0075:
            int r1 = r1 + 1
            goto L_0x0048
        L_0x0078:
            int r10 = r9.j     // Catch:{ all -> 0x0028 }
            r1 = 1
            int r10 = r10 + r1
            r9.j = r10     // Catch:{ all -> 0x0028 }
            r10 = 0
            r0.d = r10     // Catch:{ all -> 0x0028 }
            boolean r10 = r0.c     // Catch:{ all -> 0x0028 }
            r10 = r10 | r11
            r2 = 10
            if (r10 == 0) goto L_0x00b8
            r0.c = r1     // Catch:{ all -> 0x0028 }
            java.io.Writer r10 = r9.i     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0028 }
            r1.<init>()     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = "CLEAN "
            r1.append(r3)     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = r0.a     // Catch:{ all -> 0x0028 }
            r1.append(r3)     // Catch:{ all -> 0x0028 }
            java.lang.String r3 = r0.a()     // Catch:{ all -> 0x0028 }
            r1.append(r3)     // Catch:{ all -> 0x0028 }
            r1.append(r2)     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0028 }
            r10.write(r1)     // Catch:{ all -> 0x0028 }
            if (r11 == 0) goto L_0x00da
            long r10 = r9.m     // Catch:{ all -> 0x0028 }
            r1 = 1
            long r1 = r1 + r10
            r9.m = r1     // Catch:{ all -> 0x0028 }
            r0.e = r10     // Catch:{ all -> 0x0028 }
            goto L_0x00da
        L_0x00b8:
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r10 = r9.h     // Catch:{ all -> 0x0028 }
            java.lang.String r11 = r0.a     // Catch:{ all -> 0x0028 }
            r10.remove(r11)     // Catch:{ all -> 0x0028 }
            java.io.Writer r10 = r9.i     // Catch:{ all -> 0x0028 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0028 }
            r11.<init>()     // Catch:{ all -> 0x0028 }
            java.lang.String r1 = "REMOVE "
            r11.append(r1)     // Catch:{ all -> 0x0028 }
            java.lang.String r0 = r0.a     // Catch:{ all -> 0x0028 }
            r11.append(r0)     // Catch:{ all -> 0x0028 }
            r11.append(r2)     // Catch:{ all -> 0x0028 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0028 }
            r10.write(r11)     // Catch:{ all -> 0x0028 }
        L_0x00da:
            java.io.Writer r10 = r9.i     // Catch:{ all -> 0x0028 }
            r10.flush()     // Catch:{ all -> 0x0028 }
            long r10 = r9.l     // Catch:{ all -> 0x0028 }
            long r0 = r9.k     // Catch:{ all -> 0x0028 }
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 > 0) goto L_0x00ed
            boolean r10 = r9.g()     // Catch:{ all -> 0x0028 }
            if (r10 == 0) goto L_0x00f4
        L_0x00ed:
            java.util.concurrent.ThreadPoolExecutor r10 = r9.a     // Catch:{ all -> 0x0028 }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.f44n     // Catch:{ all -> 0x0028 }
            r10.submit(r11)     // Catch:{ all -> 0x0028 }
        L_0x00f4:
            monitor-exit(r9)
            return
        L_0x00f6:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0028 }
            r10.<init>()     // Catch:{ all -> 0x0028 }
            throw r10     // Catch:{ all -> 0x0028 }
        L_0x00fc:
            monitor-exit(r9)     // Catch:{ all -> 0x0028 }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.a(com.salesforce.marketingcloud.util.f$c, boolean):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:33|34|28|29) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r10.j++;
        r10.i.append("READ " + r11 + 10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0059, code lost:
        if (g() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        r10.a.submit(r10.f44n);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006f, code lost:
        return new com.salesforce.marketingcloud.util.f.e(r10, r11, r0.e, r8, r0.b);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007f, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:28:0x0070 */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.salesforce.marketingcloud.util.f.e b(java.lang.String r11) throws java.io.IOException {
        /*
            r10 = this;
            monitor-enter(r10)
            r10.a()     // Catch:{ all -> 0x0032 }
            r10.e(r11)     // Catch:{ all -> 0x0032 }
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r0 = r10.h     // Catch:{ all -> 0x0032 }
            java.lang.Object r0 = r0.get(r11)     // Catch:{ all -> 0x0032 }
            com.salesforce.marketingcloud.util.f$d r0 = (com.salesforce.marketingcloud.util.f.d) r0     // Catch:{ all -> 0x0032 }
            r1 = 0
            if (r0 != 0) goto L_0x0014
            monitor-exit(r10)
            return r1
        L_0x0014:
            boolean r2 = r0.c     // Catch:{ all -> 0x0032 }
            if (r2 != 0) goto L_0x001a
            monitor-exit(r10)
            return r1
        L_0x001a:
            int r2 = r10.c     // Catch:{ all -> 0x0032 }
            java.io.InputStream[] r8 = new java.io.InputStream[r2]     // Catch:{ all -> 0x0032 }
            r2 = 0
            r3 = r2
        L_0x0020:
            int r4 = r10.c     // Catch:{ FileNotFoundException -> 0x0070 }
            if (r3 >= r4) goto L_0x0034
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0070 }
            java.io.File r5 = r0.a((int) r3)     // Catch:{ FileNotFoundException -> 0x0070 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0070 }
            r8[r3] = r4     // Catch:{ FileNotFoundException -> 0x0070 }
            int r3 = r3 + 1
            goto L_0x0020
        L_0x0032:
            r11 = move-exception
            goto L_0x0080
        L_0x0034:
            int r1 = r10.j     // Catch:{ all -> 0x0032 }
            int r1 = r1 + 1
            r10.j = r1     // Catch:{ all -> 0x0032 }
            java.io.Writer r1 = r10.i     // Catch:{ all -> 0x0032 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0032 }
            r2.<init>()     // Catch:{ all -> 0x0032 }
            java.lang.String r3 = "READ "
            r2.append(r3)     // Catch:{ all -> 0x0032 }
            r2.append(r11)     // Catch:{ all -> 0x0032 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0032 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0032 }
            r1.append(r2)     // Catch:{ all -> 0x0032 }
            boolean r1 = r10.g()     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0062
            java.util.concurrent.ThreadPoolExecutor r1 = r10.a     // Catch:{ all -> 0x0032 }
            java.util.concurrent.Callable<java.lang.Void> r2 = r10.f44n     // Catch:{ all -> 0x0032 }
            r1.submit(r2)     // Catch:{ all -> 0x0032 }
        L_0x0062:
            com.salesforce.marketingcloud.util.f$e r1 = new com.salesforce.marketingcloud.util.f$e     // Catch:{ all -> 0x0032 }
            long r6 = r0.e     // Catch:{ all -> 0x0032 }
            long[] r9 = r0.b     // Catch:{ all -> 0x0032 }
            r3 = r1
            r4 = r10
            r5 = r11
            r3.<init>(r5, r6, r8, r9)     // Catch:{ all -> 0x0032 }
            monitor-exit(r10)
            return r1
        L_0x0070:
            int r11 = r10.c     // Catch:{ all -> 0x0032 }
            if (r2 >= r11) goto L_0x007e
            r11 = r8[r2]     // Catch:{ all -> 0x0032 }
            if (r11 == 0) goto L_0x007e
            com.salesforce.marketingcloud.util.g.a((java.io.Closeable) r11)     // Catch:{ all -> 0x0032 }
            int r2 = r2 + 1
            goto L_0x0070
        L_0x007e:
            monitor-exit(r10)
            return r1
        L_0x0080:
            monitor-exit(r10)     // Catch:{ all -> 0x0032 }
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.b(java.lang.String):com.salesforce.marketingcloud.util.f$e");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x008a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean d(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.a()     // Catch:{ all -> 0x0044 }
            r7.e(r8)     // Catch:{ all -> 0x0044 }
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r0 = r7.h     // Catch:{ all -> 0x0044 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0044 }
            com.salesforce.marketingcloud.util.f$d r0 = (com.salesforce.marketingcloud.util.f.d) r0     // Catch:{ all -> 0x0044 }
            r1 = 0
            if (r0 == 0) goto L_0x008b
            com.salesforce.marketingcloud.util.f$c r2 = r0.d     // Catch:{ all -> 0x0044 }
            if (r2 == 0) goto L_0x0018
            goto L_0x008b
        L_0x0018:
            int r2 = r7.c     // Catch:{ all -> 0x0044 }
            if (r1 >= r2) goto L_0x0056
            java.io.File r2 = r0.a((int) r1)     // Catch:{ all -> 0x0044 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x0046
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0044 }
            if (r3 == 0) goto L_0x002d
            goto L_0x0046
        L_0x002d:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r0.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0044 }
            r0.append(r2)     // Catch:{ all -> 0x0044 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0044 }
            r8.<init>(r0)     // Catch:{ all -> 0x0044 }
            throw r8     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r8 = move-exception
            goto L_0x008d
        L_0x0046:
            long r2 = r7.l     // Catch:{ all -> 0x0044 }
            long[] r4 = r0.b     // Catch:{ all -> 0x0044 }
            r5 = r4[r1]     // Catch:{ all -> 0x0044 }
            long r2 = r2 - r5
            r7.l = r2     // Catch:{ all -> 0x0044 }
            r2 = 0
            r4[r1] = r2     // Catch:{ all -> 0x0044 }
            int r1 = r1 + 1
            goto L_0x0018
        L_0x0056:
            int r0 = r7.j     // Catch:{ all -> 0x0044 }
            r1 = 1
            int r0 = r0 + r1
            r7.j = r0     // Catch:{ all -> 0x0044 }
            java.io.Writer r0 = r7.i     // Catch:{ all -> 0x0044 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
            r2.<init>()     // Catch:{ all -> 0x0044 }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x0044 }
            r2.append(r8)     // Catch:{ all -> 0x0044 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0044 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0044 }
            r0.append(r2)     // Catch:{ all -> 0x0044 }
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r0 = r7.h     // Catch:{ all -> 0x0044 }
            r0.remove(r8)     // Catch:{ all -> 0x0044 }
            boolean r8 = r7.g()     // Catch:{ all -> 0x0044 }
            if (r8 == 0) goto L_0x0089
            java.util.concurrent.ThreadPoolExecutor r8 = r7.a     // Catch:{ all -> 0x0044 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.f44n     // Catch:{ all -> 0x0044 }
            r8.submit(r0)     // Catch:{ all -> 0x0044 }
        L_0x0089:
            monitor-exit(r7)
            return r1
        L_0x008b:
            monitor-exit(r7)
            return r1
        L_0x008d:
            monitor-exit(r7)     // Catch:{ all -> 0x0044 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.d(java.lang.String):boolean");
    }

    private static void a(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    public c a(String str) throws IOException {
        return a(str, -1);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.salesforce.marketingcloud.util.f.c a(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.a()     // Catch:{ all -> 0x001f }
            r5.e(r6)     // Catch:{ all -> 0x001f }
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r0 = r5.h     // Catch:{ all -> 0x001f }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x001f }
            com.salesforce.marketingcloud.util.f$d r0 = (com.salesforce.marketingcloud.util.f.d) r0     // Catch:{ all -> 0x001f }
            r1 = -1
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L_0x0023
            if (r0 == 0) goto L_0x0021
            long r3 = r0.e     // Catch:{ all -> 0x001f }
            int r7 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0023
            goto L_0x0021
        L_0x001f:
            r6 = move-exception
            goto L_0x005f
        L_0x0021:
            monitor-exit(r5)
            return r2
        L_0x0023:
            if (r0 != 0) goto L_0x0030
            com.salesforce.marketingcloud.util.f$d r0 = new com.salesforce.marketingcloud.util.f$d     // Catch:{ all -> 0x001f }
            r0.<init>(r6)     // Catch:{ all -> 0x001f }
            java.util.LinkedHashMap<java.lang.String, com.salesforce.marketingcloud.util.f$d> r7 = r5.h     // Catch:{ all -> 0x001f }
            r7.put(r6, r0)     // Catch:{ all -> 0x001f }
            goto L_0x0036
        L_0x0030:
            com.salesforce.marketingcloud.util.f$c r7 = r0.d     // Catch:{ all -> 0x001f }
            if (r7 == 0) goto L_0x0036
            monitor-exit(r5)
            return r2
        L_0x0036:
            com.salesforce.marketingcloud.util.f$c r7 = new com.salesforce.marketingcloud.util.f$c     // Catch:{ all -> 0x001f }
            r7.<init>(r0)     // Catch:{ all -> 0x001f }
            r0.d = r7     // Catch:{ all -> 0x001f }
            java.io.Writer r8 = r5.i     // Catch:{ all -> 0x001f }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x001f }
            r0.<init>()     // Catch:{ all -> 0x001f }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x001f }
            r0.append(r6)     // Catch:{ all -> 0x001f }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x001f }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x001f }
            r8.write(r6)     // Catch:{ all -> 0x001f }
            java.io.Writer r6 = r5.i     // Catch:{ all -> 0x001f }
            r6.flush()     // Catch:{ all -> 0x001f }
            monitor-exit(r5)
            return r7
        L_0x005f:
            monitor-exit(r5)     // Catch:{ all -> 0x001f }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.util.f.a(java.lang.String, long):com.salesforce.marketingcloud.util.f$c");
    }

    static String a(InputStream inputStream) throws IOException {
        return g.a((Reader) new InputStreamReader(inputStream, g.c));
    }

    public static f a(File file, int i2, int i3, long j2) throws IOException {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i3 > 0) {
            File file2 = new File(file, q);
            if (file2.exists()) {
                File file3 = new File(file, o);
                if (file3.exists()) {
                    file2.delete();
                } else {
                    a(file2, file3, false);
                }
            }
            f fVar = new f(file, i2, i3, j2);
            if (fVar.d.exists()) {
                try {
                    fVar.i();
                    fVar.h();
                    return fVar;
                } catch (IOException e2) {
                    g.b("DiskLruCache", e2, "DiskLruCache %s is corrupt, removing.", file);
                    fVar.b();
                }
            }
            file.mkdirs();
            f fVar2 = new f(file, i2, i3, j2);
            fVar2.j();
            return fVar2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    private static void a(File file, File file2, boolean z2) throws IOException {
        if (z2) {
            a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    public synchronized void a(long j2) {
        this.k = j2;
        this.a.submit(this.f44n);
    }
}
