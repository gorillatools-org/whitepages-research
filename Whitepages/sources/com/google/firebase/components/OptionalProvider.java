package com.google.firebase.components;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

class OptionalProvider<T> implements Provider<T>, Deferred<T> {
    private static final Provider<Object> EMPTY_PROVIDER = new OptionalProvider$$ExternalSyntheticLambda1();
    private static final Deferred.DeferredHandler<Object> NOOP_HANDLER = new OptionalProvider$$ExternalSyntheticLambda0();
    private volatile Provider<T> delegate;
    private Deferred.DeferredHandler<T> handler;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0(Provider provider) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object lambda$static$1() {
        return null;
    }

    private OptionalProvider(Deferred.DeferredHandler<T> deferredHandler, Provider<T> provider) {
        this.handler = deferredHandler;
        this.delegate = provider;
    }

    static <T> OptionalProvider<T> empty() {
        return new OptionalProvider<>(NOOP_HANDLER, EMPTY_PROVIDER);
    }

    static <T> OptionalProvider<T> of(Provider<T> provider) {
        return new OptionalProvider<>((Deferred.DeferredHandler) null, provider);
    }

    public T get() {
        return this.delegate.get();
    }

    /* access modifiers changed from: package-private */
    public void set(Provider<T> provider) {
        Deferred.DeferredHandler<T> deferredHandler;
        if (this.delegate == EMPTY_PROVIDER) {
            synchronized (this) {
                deferredHandler = this.handler;
                this.handler = null;
                this.delegate = provider;
            }
            deferredHandler.handle(provider);
            return;
        }
        throw new IllegalStateException("provide() can be called only once.");
    }

    public void whenAvailable(Deferred.DeferredHandler<T> deferredHandler) {
        Provider<T> provider;
        Provider<T> provider2;
        Provider<T> provider3 = this.delegate;
        Provider<Object> provider4 = EMPTY_PROVIDER;
        if (provider3 != provider4) {
            deferredHandler.handle(provider3);
            return;
        }
        synchronized (this) {
            provider = this.delegate;
            if (provider != provider4) {
                provider2 = provider;
            } else {
                this.handler = new OptionalProvider$$ExternalSyntheticLambda2(this.handler, deferredHandler);
                provider2 = null;
            }
        }
        if (provider2 != null) {
            deferredHandler.handle(provider);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$whenAvailable$2(Deferred.DeferredHandler deferredHandler, Deferred.DeferredHandler deferredHandler2, Provider provider) {
        deferredHandler.handle(provider);
        deferredHandler2.handle(provider);
    }
}
