package androidx.datastore.core;

import java.util.concurrent.atomic.AtomicInteger;

public final class AtomicInt {
    private final AtomicInteger delegate;

    public AtomicInt(int i) {
        this.delegate = new AtomicInteger(i);
    }

    public final int getAndIncrement() {
        return this.delegate.getAndIncrement();
    }

    public final int decrementAndGet() {
        return this.delegate.decrementAndGet();
    }

    public final int get() {
        return this.delegate.get();
    }

    public final int incrementAndGet() {
        return this.delegate.incrementAndGet();
    }
}
