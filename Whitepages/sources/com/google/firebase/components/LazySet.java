package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class LazySet<T> implements Provider<Set<T>> {
    private volatile Set<T> actualSet = null;
    private volatile Set<Provider<T>> providers = Collections.newSetFromMap(new ConcurrentHashMap());

    LazySet(Collection<Provider<T>> collection) {
        this.providers.addAll(collection);
    }

    static LazySet<?> fromCollection(Collection<Provider<?>> collection) {
        return new LazySet<>((Set) collection);
    }

    public Set<T> get() {
        if (this.actualSet == null) {
            synchronized (this) {
                try {
                    if (this.actualSet == null) {
                        this.actualSet = Collections.newSetFromMap(new ConcurrentHashMap());
                        updateSet();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return Collections.unmodifiableSet(this.actualSet);
    }

    /* access modifiers changed from: package-private */
    public synchronized void add(Provider<T> provider) {
        try {
            if (this.actualSet == null) {
                this.providers.add(provider);
            } else {
                this.actualSet.add(provider.get());
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    private synchronized void updateSet() {
        try {
            for (Provider<T> provider : this.providers) {
                this.actualSet.add(provider.get());
            }
            this.providers = null;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
}
