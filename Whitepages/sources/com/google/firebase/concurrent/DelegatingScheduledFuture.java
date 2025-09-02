package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import androidx.concurrent.futures.AbstractResolvableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SuppressLint({"RestrictedApi"})
class DelegatingScheduledFuture<V> extends AbstractResolvableFuture implements ScheduledFuture<V> {
    private final ScheduledFuture<?> upstreamFuture;

    interface Completer<T> {
        void set(T t);

        void setException(Throwable th);
    }

    interface Resolver<T> {
        ScheduledFuture<?> addCompleter(Completer<T> completer);
    }

    DelegatingScheduledFuture(Resolver<V> resolver) {
        this.upstreamFuture = resolver.addCompleter(new Completer<V>() {
            public void set(V v) {
                boolean unused = DelegatingScheduledFuture.this.set(v);
            }

            public void setException(Throwable th) {
                boolean unused = DelegatingScheduledFuture.this.setException(th);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
        this.upstreamFuture.cancel(wasInterrupted());
    }

    public long getDelay(TimeUnit timeUnit) {
        return this.upstreamFuture.getDelay(timeUnit);
    }

    public int compareTo(Delayed delayed) {
        return this.upstreamFuture.compareTo(delayed);
    }
}
