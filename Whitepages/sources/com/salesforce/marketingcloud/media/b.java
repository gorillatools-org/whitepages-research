package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.s;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"UnknownNullness"})
public class b implements f {
    private static final String g = g.a("BatchRequestHandler");
    private final AtomicInteger a;
    private final o b;
    private final List<String> c;
    private a d;
    private boolean e;
    private boolean f;

    public interface a {
        void a(boolean z);
    }

    public b(o oVar, List<String> list) {
        this.b = oVar;
        this.c = list;
        this.a = new AtomicInteger(list.size());
    }

    private void c() {
        a aVar;
        if (this.a.decrementAndGet() <= 0 && (aVar = this.d) != null && !this.e) {
            aVar.a(!this.f);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
        if (this.a.get() != 0) {
            for (String b2 : this.c) {
                this.b.b(b2).a(s.b.NO_MEMORY_CACHE, s.b.NO_MEMORY_STORE).a((f) this);
            }
        } else if (aVar != null) {
            aVar.a(true);
        }
    }

    public void b() {
        this.e = true;
    }

    public void a(Exception exc) {
        if (exc instanceof k) {
            g.b(g, exc, "Failed to pre-fetch image, but will be ignored since the url cannot be handled.", new Object[0]);
        } else {
            this.f = true;
            g.b(g, exc, "Failed to pre-fetch image.", new Object[0]);
        }
        c();
    }

    public void a() {
        c();
    }
}
