package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.net.Uri;
import com.salesforce.marketingcloud.media.o;

@SuppressLint({"UnknownNullness"})
public class s {
    static final char m = '\n';
    public final Uri a;
    public final String b = a();
    public final o.c c;
    public final int d;
    public final int e;
    public final int f;
    public final boolean g;
    public final boolean h;
    public final float i;
    public final float j;
    public final int k;
    public long l;

    public static class a {
        Uri a;
        o.c b;
        int c;
        int d;
        int e;
        boolean f;
        boolean g;
        float h;
        float i;
        int j;

        public a(Uri uri) {
            this.a = uri;
        }

        public s a() {
            if (this.b == null) {
                this.b = o.c.NORMAL;
            }
            return new s(this);
        }

        public a b() {
            this.f = true;
            return this;
        }

        public a c() {
            this.g = true;
            return this;
        }

        public boolean d() {
            return this.b != null;
        }

        public a a(float f2, float f3, int i2) {
            this.h = f2;
            this.i = f3;
            this.j = i2;
            return this;
        }

        public a a(int i2, int i3) {
            this.d = i2;
            this.e = i3;
            return this;
        }

        public a a(o.c cVar) {
            this.b = cVar;
            return this;
        }

        public a a(b bVar, b... bVarArr) {
            if (bVar == null) {
                return this;
            }
            this.c = bVar.a | this.c;
            if (bVarArr == null) {
                return this;
            }
            for (b bVar2 : bVarArr) {
                this.c = bVar2.a | this.c;
            }
            return this;
        }
    }

    public enum b {
        NO_MEMORY_CACHE(1),
        NO_MEMORY_STORE(2),
        NO_DISK_STORE(4);
        
        int a;

        private b(int i) {
            this.a = i;
        }

        public static boolean b(int i) {
            return (i & NO_MEMORY_STORE.a) == 0;
        }

        public static boolean c(int i) {
            return (i & NO_DISK_STORE.a) == 0;
        }

        public static boolean a(int i) {
            return (i & NO_MEMORY_CACHE.a) == 0;
        }

        public int b() {
            return this.a;
        }
    }

    s(a aVar) {
        this.a = aVar.a;
        this.c = aVar.b;
        this.d = aVar.c;
        this.e = aVar.d;
        this.f = aVar.e;
        this.g = aVar.f;
        this.h = aVar.g;
        this.i = aVar.h;
        this.j = aVar.i;
        this.k = aVar.j;
    }

    private String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a.toString());
        sb.append(m);
        if (d()) {
            sb.append("resize:");
            sb.append(this.e);
            sb.append('x');
            sb.append(this.f);
            sb.append(m);
        }
        if (this.g) {
            sb.append("centerCrop");
            sb.append(m);
        }
        if (this.h) {
            sb.append("centerInside");
            sb.append(m);
        }
        if (c()) {
            sb.append("radius:");
            sb.append(this.i);
            sb.append(",border:");
            sb.append(this.j);
            sb.append(",color:");
            sb.append(this.k);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String b() {
        return String.valueOf(this.a.getPath());
    }

    /* access modifiers changed from: package-private */
    public boolean c() {
        return (this.i == 0.0f && this.j == 0.0f) ? false : true;
    }

    public boolean d() {
        return (this.e == 0 && this.f == 0) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public boolean e() {
        return d() || c();
    }
}
