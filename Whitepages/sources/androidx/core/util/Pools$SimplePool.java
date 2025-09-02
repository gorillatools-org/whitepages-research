package androidx.core.util;

import kotlin.jvm.internal.Intrinsics;

public class Pools$SimplePool implements Pools$Pool {
    private final Object[] pool;
    private int poolSize;

    public Pools$SimplePool(int i) {
        if (i > 0) {
            this.pool = new Object[i];
            return;
        }
        throw new IllegalArgumentException("The max pool size must be > 0");
    }

    public Object acquire() {
        int i = this.poolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object obj = this.pool[i2];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.core.util.Pools.SimplePool");
        this.pool[i2] = null;
        this.poolSize--;
        return obj;
    }

    public boolean release(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "instance");
        if (!isInPool(obj)) {
            int i = this.poolSize;
            Object[] objArr = this.pool;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = obj;
            this.poolSize = i + 1;
            return true;
        }
        throw new IllegalStateException("Already in the pool!");
    }

    private final boolean isInPool(Object obj) {
        int i = this.poolSize;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.pool[i2] == obj) {
                return true;
            }
        }
        return false;
    }
}
