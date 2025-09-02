package com.salesforce.marketingcloud.media;

import com.salesforce.marketingcloud.util.f;
import com.salesforce.marketingcloud.util.g;
import com.salesforce.marketingcloud.util.l;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class r {
    private static final int d = 20971520;
    private final File a;
    private final Object b = new Object();
    private f c;

    r(File file) {
        this.a = file;
    }

    private void b() throws IOException {
        synchronized (this.b) {
            try {
                if (this.c == null) {
                    this.c = f.a(this.a, 0, 1, 20971520);
                    this.b.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static String c(String str) {
        return l.g(str);
    }

    /* access modifiers changed from: package-private */
    public void a() throws IOException {
        b();
        this.c.c();
    }

    /* access modifiers changed from: package-private */
    public InputStream a(String str) throws IOException {
        InputStream a2;
        b();
        String c2 = c(str);
        synchronized (this.b) {
            try {
                f.e b2 = this.c.b(c2);
                a2 = b2 != null ? b2.a(0) : null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return a2;
    }

    /* access modifiers changed from: package-private */
    public void b(String str) throws IOException {
        b();
        this.c.d(c(str));
    }

    /* access modifiers changed from: package-private */
    public void a(String str, InputStream inputStream) throws IOException {
        b();
        String c2 = c(str);
        synchronized (this.b) {
            try {
                f.c a2 = this.c.a(c2);
                g.a(inputStream, a2.c(0));
                a2.c();
                g.a((Closeable) inputStream);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
