package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import com.salesforce.marketingcloud.media.u;
import com.salesforce.marketingcloud.storage.j;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@SuppressLint({"UnknownNullness"})
public class o {
    static final Handler i = new a(Looper.getMainLooper());
    final Context a;
    final h b;
    final c c;
    final r d;
    final Map<ImageView, g> e = new WeakHashMap();
    final Map<Object, a> f = new WeakHashMap();
    private final List<u> g;
    public ReferenceQueue h;

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                n nVar = (n) message.obj;
                nVar.a.a(nVar);
            } else if (i == 5) {
                e eVar = (e) message.obj;
                eVar.a.a(eVar);
            }
        }
    }

    public enum b {
        MEMORY(-16711936),
        DISK(-16776961),
        NETWORK(-65536);
        
        final int a;

        private b(int i) {
            this.a = i;
        }
    }

    public enum c {
        NORMAL,
        HIGH
    }

    public o(Context context, h hVar, c cVar, r rVar) {
        this.a = context;
        this.b = hVar;
        this.c = cVar;
        this.d = rVar;
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new q(rVar));
        arrayList.add(new i(context));
        this.g = Collections.unmodifiableList(arrayList);
    }

    public b a(List<String> list) {
        return new b(this, new ArrayList(list));
    }

    public t b(String str) {
        return new t(this, Uri.parse(str));
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj) {
        g remove;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            a remove2 = this.f.remove(obj);
            if (remove2 != null) {
                remove2.a();
                this.b.a(remove2);
            }
            if ((obj instanceof ImageView) && (remove = this.e.remove((ImageView) obj)) != null) {
                remove.a();
            }
        }
    }

    public Bitmap a(String str) {
        return this.c.a(str);
    }

    public void a(e eVar) {
        f b2 = eVar.b.b();
        if (b2 == null) {
            return;
        }
        if (eVar.b()) {
            b2.a(eVar.a());
        } else {
            b2.a();
        }
    }

    public void a(n nVar) {
        a c2 = nVar.c();
        List<a> d2 = nVar.d();
        boolean z = d2 != null && !d2.isEmpty();
        if (c2 != null || z) {
            Exception f2 = nVar.f();
            u.b i2 = nVar.i();
            if (c2 != null) {
                a(c2, i2, f2);
            }
            if (z) {
                int size = d2.size();
                for (int i3 = 0; i3 < size; i3++) {
                    a(d2.get(i3), i2, f2);
                }
            }
        }
    }

    public static o a(Context context, j jVar) {
        c cVar = new c(context);
        return new o(context, new h(context, new m(), i, cVar), cVar, new r(jVar.k()));
    }

    public void a(ImageView imageView, g gVar) {
        if (this.e.containsKey(imageView)) {
            a((Object) imageView);
        }
        this.e.put(imageView, gVar);
    }

    private void a(a aVar, u.b bVar, Exception exc) {
        if (!aVar.g()) {
            this.f.remove(aVar.f());
            if (bVar != null) {
                aVar.a(bVar);
            } else {
                aVar.a(exc);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<u> a() {
        return this.g;
    }

    public void a(Collection<String> collection) {
        a(collection, (f) null);
    }

    public void a(Collection<String> collection, f fVar) {
        if (collection != null && !collection.isEmpty()) {
            this.b.a(new d(this, new ArrayList(collection), this.d, fVar));
        }
    }

    public void a(a aVar) {
        Object f2 = aVar.f();
        if (!(f2 == null || this.f.get(f2) == aVar)) {
            a(f2);
            this.f.put(f2, aVar);
        }
        this.b.b(aVar);
    }
}
