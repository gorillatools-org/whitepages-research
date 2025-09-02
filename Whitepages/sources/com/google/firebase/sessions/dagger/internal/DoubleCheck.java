package com.google.firebase.sessions.dagger.internal;

import com.google.firebase.sessions.dagger.Lazy;
import javax.inject.Provider;

public final class DoubleCheck<T> implements Provider, Lazy<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private DoubleCheck(Provider provider2) {
        this.provider = provider2;
    }

    public T get() {
        T t = this.instance;
        T t2 = UNINITIALIZED;
        if (t == t2) {
            synchronized (this) {
                try {
                    t = this.instance;
                    if (t == t2) {
                        t = this.provider.get();
                        this.instance = reentrantCheck(this.instance, t);
                        this.provider = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return t;
    }

    private static Object reentrantCheck(Object obj, Object obj2) {
        if (obj == UNINITIALIZED || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    public static <P extends Provider, T> Provider provider(P p) {
        Preconditions.checkNotNull(p);
        if (p instanceof DoubleCheck) {
            return p;
        }
        return new DoubleCheck(p);
    }

    public static <P extends Provider, T> Lazy<T> lazy(P p) {
        if (p instanceof Lazy) {
            return (Lazy) p;
        }
        return new DoubleCheck((Provider) Preconditions.checkNotNull(p));
    }
}
