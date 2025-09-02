package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

public final class DoubleCheck implements Provider {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private DoubleCheck(Provider provider2) {
        this.provider = provider2;
    }

    public Object get() {
        Object obj = this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                try {
                    obj = this.instance;
                    if (obj == obj2) {
                        obj = this.provider.get();
                        this.instance = reentrantCheck(this.instance, obj);
                        this.provider = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return obj;
    }

    private static Object reentrantCheck(Object obj, Object obj2) {
        if (obj == UNINITIALIZED || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    public static Provider provider(Provider provider2) {
        Preconditions.checkNotNull(provider2);
        if (provider2 instanceof DoubleCheck) {
            return provider2;
        }
        return new DoubleCheck(provider2);
    }
}
