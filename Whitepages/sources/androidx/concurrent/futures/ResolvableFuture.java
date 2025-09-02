package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;

public final class ResolvableFuture extends AbstractResolvableFuture {
    public static ResolvableFuture create() {
        return new ResolvableFuture();
    }

    public boolean set(Object obj) {
        return super.set(obj);
    }

    public boolean setException(Throwable th) {
        return super.setException(th);
    }

    public boolean setFuture(ListenableFuture listenableFuture) {
        return super.setFuture(listenableFuture);
    }

    private ResolvableFuture() {
    }
}
