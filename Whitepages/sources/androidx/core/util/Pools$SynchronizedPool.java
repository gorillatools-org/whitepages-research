package androidx.core.util;

import kotlin.jvm.internal.Intrinsics;

public class Pools$SynchronizedPool extends Pools$SimplePool {
    private final Object lock = new Object();

    public Pools$SynchronizedPool(int i) {
        super(i);
    }

    public Object acquire() {
        Object acquire;
        synchronized (this.lock) {
            acquire = super.acquire();
        }
        return acquire;
    }

    public boolean release(Object obj) {
        boolean release;
        Intrinsics.checkNotNullParameter(obj, "instance");
        synchronized (this.lock) {
            release = super.release(obj);
        }
        return release;
    }
}
