package com.salesforce.marketingcloud.media;

class e implements Runnable {
    final o a;
    final d b;
    final h c;
    private Exception d;

    e(h hVar, d dVar) {
        this.a = dVar.c();
        this.c = hVar;
        this.b = dVar;
    }

    /* access modifiers changed from: package-private */
    public Exception a() {
        return this.d;
    }

    /* access modifiers changed from: package-private */
    public boolean b() {
        return this.d != null;
    }

    public void run() {
        try {
            Thread.currentThread().setName("CacheCleaner - Cleaning");
            r a2 = this.b.a();
            for (String b2 : this.b.d()) {
                a2.b(b2);
            }
            a2.a();
        } catch (Exception e) {
            this.d = e;
        } catch (Throwable th) {
            Thread.currentThread().setName("CacheCleaner - Idle");
            throw th;
        }
        Thread.currentThread().setName("CacheCleaner - Idle");
        this.c.a(this);
    }
}
