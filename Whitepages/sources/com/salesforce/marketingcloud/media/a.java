package com.salesforce.marketingcloud.media;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.media.o;
import com.salesforce.marketingcloud.media.u;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

@SuppressLint({"UnknownNullness"})
public abstract class a<T> {
    final s a;
    final WeakReference<T> b;
    final v<T> c;
    private final o d;
    private boolean e;

    /* renamed from: com.salesforce.marketingcloud.media.a$a  reason: collision with other inner class name */
    static class C0021a<M> extends WeakReference<M> {
        final a a;

        C0021a(a aVar, M m, ReferenceQueue<? super M> referenceQueue) {
            super(m, referenceQueue);
            this.a = aVar;
        }
    }

    a(o oVar, v<T> vVar, s sVar) {
        this.d = oVar;
        this.a = sVar;
        if (vVar == null) {
            this.b = null;
        } else {
            this.b = new C0021a(this, vVar.a, oVar.h);
            vVar.a = null;
        }
        this.c = vVar;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.e = true;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(u.b bVar);

    /* access modifiers changed from: package-private */
    public abstract void a(Exception exc);

    /* access modifiers changed from: package-private */
    public o b() {
        return this.d;
    }

    public String c() {
        return this.a.b;
    }

    public o.c d() {
        return this.a.c;
    }

    /* access modifiers changed from: package-private */
    public s e() {
        return this.a;
    }

    /* access modifiers changed from: package-private */
    public T f() {
        WeakReference<T> weakReference = this.b;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public boolean g() {
        return this.e;
    }
}
