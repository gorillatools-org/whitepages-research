package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.salesforce.marketingcloud.g;
import com.salesforce.marketingcloud.media.s;
import com.salesforce.marketingcloud.media.u;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@SuppressLint({"UnknownNullness"})
public class h {
    static final int h = 1;
    static final int i = 2;
    static final int j = 3;
    static final int k = 4;
    static final int l = 5;
    static final int m = 6;

    /* renamed from: n  reason: collision with root package name */
    private static final String f30n = g.a("Dispatcher");
    final Map<String, n> a = new LinkedHashMap();
    final b b;
    final Context c;
    final ExecutorService d;
    final Handler e;
    final Handler f;
    final c g;

    private static class a extends Handler {
        private final h a;

        a(Looper looper, h hVar) {
            super(looper);
            this.a = hVar;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.d((a) message.obj);
                    return;
                case 2:
                    this.a.d((n) message.obj);
                    return;
                case 3:
                    this.a.e((n) message.obj);
                    return;
                case 4:
                    this.a.b((d) message.obj);
                    return;
                case 5:
                    this.a.b((e) message.obj);
                    return;
                case 6:
                    this.a.c((a) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    static class b extends HandlerThread {
        public b() {
            super("mcsdk_image_thread", 10);
        }
    }

    h(Context context, ExecutorService executorService, Handler handler, c cVar) {
        b bVar = new b();
        this.b = bVar;
        bVar.start();
        this.c = context;
        this.d = executorService;
        this.e = new a(bVar.getLooper(), this);
        this.f = handler;
        this.g = cVar;
    }

    private void a(n nVar) {
        if (!nVar.j()) {
            u.b i2 = nVar.i();
            if (i2 != null && i2.d()) {
                i2.a().prepareToDraw();
            }
            Handler handler = this.f;
            handler.sendMessage(handler.obtainMessage(2, nVar));
        }
    }

    public void b(n nVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(2, nVar));
    }

    public void c(n nVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(3, nVar));
    }

    /* access modifiers changed from: package-private */
    public void d(n nVar) {
        if (s.b.b(nVar.f.d)) {
            u.b bVar = nVar.i;
            if (bVar.d()) {
                this.g.a(nVar.h(), bVar.a());
            }
        }
        this.a.remove(nVar.h());
        a(nVar);
    }

    /* access modifiers changed from: package-private */
    public void e(n nVar) {
        this.a.remove(nVar.h());
        a(nVar);
    }

    public void a(d dVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(4, dVar));
    }

    public void b(a aVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(1, aVar));
    }

    /* access modifiers changed from: package-private */
    public void c(a aVar) {
        String c2 = aVar.c();
        n nVar = this.a.get(c2);
        if (nVar != null) {
            nVar.b(aVar);
            if (nVar.a()) {
                this.a.remove(c2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void d(a aVar) {
        n nVar = this.a.get(aVar.c());
        if (nVar != null) {
            nVar.a(aVar);
        } else if (this.d.isShutdown()) {
            g.a(f30n, "ExecutorService is shutdown.  Ignoring request.", new Object[0]);
        } else {
            n a2 = n.a(aVar.b(), this, this.g, aVar);
            a2.j = this.d.submit(a2);
            this.a.put(aVar.c(), a2);
        }
    }

    public void a(a aVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(6, aVar));
    }

    /* access modifiers changed from: package-private */
    public void b(d dVar) {
        if (this.d.isShutdown()) {
            g.a(f30n, "ExecutorService is shutdown.  Ignoring request.", new Object[0]);
            return;
        }
        this.d.submit(new e(this, dVar));
    }

    public void a(e eVar) {
        Handler handler = this.e;
        handler.sendMessage(handler.obtainMessage(5, eVar));
    }

    /* access modifiers changed from: package-private */
    public void b(e eVar) {
        Handler handler = this.f;
        handler.sendMessage(handler.obtainMessage(5, eVar));
    }
}
