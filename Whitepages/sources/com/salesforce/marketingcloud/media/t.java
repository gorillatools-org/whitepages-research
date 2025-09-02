package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Looper;
import android.widget.ImageView;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.s;
import com.salesforce.marketingcloud.media.u;

@SuppressLint({"UnknownNullness"})
public class t {
    private final o a;
    private final s.a b;
    private boolean c;

    public t(o oVar, Uri uri) {
        this.a = oVar;
        this.b = new s.a(uri);
    }

    public t a() {
        this.b.b();
        return this;
    }

    public t b() {
        this.b.c();
        return this;
    }

    public void c() {
        a((f) null);
    }

    public t d() {
        this.c = true;
        return this;
    }

    private s a(long j) {
        s a2 = this.b.a();
        a2.l = j;
        return a2;
    }

    public t a(float f, float f2, int i) {
        this.b.a(f, f2, i);
        return this;
    }

    public void a(f fVar) {
        long nanoTime = System.nanoTime();
        if (!this.b.d()) {
            this.b.a(o.c.NORMAL);
        }
        s a2 = a(nanoTime);
        if (!s.b.a(a2.d) || this.a.a(a2.b) == null) {
            this.a.a((a) new j(this.a, a2, fVar));
            return;
        }
        g.a("IMAGE", "onSuccess - Loaded from: MEMORY", new Object[0]);
        if (fVar != null) {
            fVar.a();
        }
    }

    public void a(ImageView imageView) {
        a(imageView, (f) null);
    }

    public void a(ImageView imageView, f fVar) {
        Bitmap a2;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            if (!this.b.d()) {
                this.b.a(o.c.HIGH);
            }
            if (this.c) {
                int width = imageView.getWidth();
                int height = imageView.getHeight();
                if (width == 0 || height == 0) {
                    this.a.a(imageView, new g(this, imageView, fVar));
                    return;
                }
                this.b.a(width, height);
            }
            s a3 = a(System.nanoTime());
            if (!s.b.a(a3.d) || (a2 = this.a.a(a3.b)) == null) {
                this.a.a((a) new p(this.a, new v(imageView), a3, fVar));
                return;
            }
            u.b bVar = new u.b(a2, o.b.MEMORY);
            l.a(imageView, this.a.a, bVar);
            g.a("IMAGE", "onSuccess - Loaded from: %s", bVar.c());
            if (fVar != null) {
                fVar.a();
                return;
            }
            return;
        }
        throw new IllegalStateException("TODO");
    }

    public t a(o.c cVar) {
        this.b.a(cVar);
        return this;
    }

    public t a(int i, int i2) {
        this.b.a(i, i2);
        return this;
    }

    public t a(s.b bVar, s.b... bVarArr) {
        this.b.a(bVar, bVarArr);
        return this;
    }
}
