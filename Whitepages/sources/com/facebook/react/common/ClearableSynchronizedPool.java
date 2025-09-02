package com.facebook.react.common;

import androidx.core.util.Pools$Pool;
import kotlin.jvm.internal.Intrinsics;

public final class ClearableSynchronizedPool<T> implements Pools$Pool {
    private final Object[] pool;
    private int size;

    public ClearableSynchronizedPool(int i) {
        this.pool = new Object[i];
    }

    public synchronized T acquire() {
        int i = this.size;
        if (i == 0) {
            return null;
        }
        int i2 = i - 1;
        this.size = i2;
        T t = this.pool[i2];
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of com.facebook.react.common.ClearableSynchronizedPool");
        this.pool[i2] = null;
        return t;
    }

    public synchronized boolean release(T t) {
        Intrinsics.checkNotNullParameter(t, "instance");
        int i = this.size;
        Object[] objArr = this.pool;
        if (i == objArr.length) {
            return false;
        }
        objArr[i] = t;
        this.size = i + 1;
        return true;
    }

    public final synchronized void clear() {
        try {
            int i = this.size;
            for (int i2 = 0; i2 < i; i2++) {
                this.pool[i2] = null;
            }
            this.size = 0;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
